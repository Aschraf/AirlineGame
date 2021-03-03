package com.haouet.airproject.view.actionpanel

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.SystemWideEvent
import com.haouet.airproject.notification.ToolBoxPressedEvent
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.view.actionpanel.actions.BuyPlaneController
import javafx.animation.TranslateTransition
import javafx.fxml.FXML
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.util.Duration

class ActionPanelController(
    notificationService: INotificationService = getService(),
) {

  @FXML lateinit var mainPane: Pane
  @FXML lateinit var tabPane: TabPane

  init {
    notificationService.addTypeListener(SystemWideEvent.EscapePressed::class) {
      close()
    }

    notificationService.addTypeListener(ToolBoxPressedEvent::class) {
      println("Listened to event: $it")
      open()
    }
  }

  @FXML
  fun initialize() {
    mainPane.managedProperty().bind(mainPane.visibleProperty())
    mainPane.isVisible = false

    val buyPlaneTab = Tab("Buy")
    buyPlaneTab.content = ResourceStore.Layout.BUY_PLANE_LAYOUT.loadRegion(BuyPlaneController())

    val dummyTab = Tab("Dummy")

    tabPane.tabs.addAll(buyPlaneTab, dummyTab)
  }


  @Suppress("MagicNumber")
  private fun open() {
    if (mainPane.isVisible) return

    val translateTransition = TranslateTransition(Duration(100.0), mainPane)
    translateTransition.fromX = -mainPane.prefWidth
    translateTransition.toX = 0.0
    translateTransition.play()

    AnchorPane.setTopAnchor(mainPane, 200.0)
    AnchorPane.setLeftAnchor(mainPane, 0.0)

    mainPane.isVisible = true
  }

  // Small lock to avoid closing twice
  private var isTransitionOn = false

  @FXML
  fun close() {
    if (isTransitionOn || !mainPane.isVisible) return
    isTransitionOn = true

    val translateTransition = TranslateTransition(Duration(100.0), mainPane)
    translateTransition.fromX = 0.0
    translateTransition.toX = -mainPane.prefWidth
    translateTransition.play()

    translateTransition.setOnFinished {
      mainPane.isVisible = false
      isTransitionOn = false
    }
  }
}