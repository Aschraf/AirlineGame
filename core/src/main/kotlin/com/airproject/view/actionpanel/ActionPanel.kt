package com.airproject.view.actionpanel

import com.airproject.binding.getService
import com.airproject.common.ResourceStore
import com.airproject.fxcomponent.loadRegion
import com.airproject.notification.GameWideEvent
import com.airproject.notification.INotificationService
import com.airproject.view.actionpanel.actions.BuyPlaneController
import javafx.animation.TranslateTransition
import javafx.fxml.FXML
import javafx.geometry.Pos
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.Pane
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.util.Duration

class ActionPanel(
    private val parentPane: Pane,
    notificationService: INotificationService = getService(),
) {

  private var isVisible = false
  private val component = loadPanel()

  @FXML lateinit var tabPane: TabPane

  init {
    notificationService.addTypeListener(GameWideEvent.EscapePressed::class) {
      close()
    }
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

    StackPane.setAlignment(panel, Pos.CENTER_LEFT);

    return panel
  }


  fun open() {
    if (isVisible) return

    val translateTransition = TranslateTransition(Duration(100.0), component)
    translateTransition.fromX = -component.prefWidth
    translateTransition.toX = 0.0
    translateTransition.play()

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