package com.haouet.airproject.view.actionpanel

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.notification.GameWideEvent
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.view.actionpanel.actions.BuyPlaneController
import com.haouet.airproject.view.events.ToolBoxPressedEvent
import javafx.animation.TranslateTransition
import javafx.fxml.FXML
import javafx.geometry.Pos
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.util.Duration

class ActionPanel(
    notificationService: INotificationService = getService(),
) {

  private lateinit var parentPane: AnchorPane
  private var isVisible = false
  private val component = loadPanel()

  @FXML lateinit var tabPane: TabPane

  init {
    notificationService.addTypeListener(GameWideEvent.EscapePressed::class) {
      close()
    }

    notificationService.addTypeListener(ToolBoxPressedEvent::class) {
      println("Listened to event: $it")
      open()
    }
  }


  fun addTo(parentPane: AnchorPane) {
    this.parentPane = parentPane
  }

  @FXML
  fun initialize() {
    val buyPlaneTab = Tab("Buy")
    buyPlaneTab.content = ResourceStore.Layout.BUY_PLANE_LAYOUT.loadRegion(BuyPlaneController())

    val dummyTab = Tab("Dummy")

    tabPane.tabs.addAll(buyPlaneTab, dummyTab)
  }

  private fun loadPanel(): Region {
    val panel = ResourceStore.Layout.ACTION_PANEL.loadRegion(this)

    StackPane.setAlignment(panel, Pos.CENTER_LEFT)

    return panel
  }


  private fun open() {
    if (isVisible) return

    val translateTransition = TranslateTransition(Duration(100.0), component)
    translateTransition.fromX = -component.prefWidth
    translateTransition.toX = 0.0
    translateTransition.play()

    AnchorPane.setTopAnchor(component, 200.0)
    AnchorPane.setLeftAnchor(component, 0.0)

    parentPane.children.add(component)
    isVisible = true
  }

  @FXML
  fun close() {
    if (!isVisible) return

    val translateTransition = TranslateTransition(Duration(100.0), component)
    translateTransition.fromX = 0.0
    translateTransition.toX = -component.prefWidth
    translateTransition.play()

    translateTransition.setOnFinished {
      parentPane.children.remove(component)
      isVisible = false
    }
  }
}