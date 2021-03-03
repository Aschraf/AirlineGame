package com.haouet.airproject.view

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.notification.GameWideEvent
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.view.actionpanel.ActionPanelController
import com.haouet.airproject.view.dynamicimage.MapCanvas
import com.haouet.airproject.view.map.MapComponentsHandler
import com.haouet.airproject.view.toolbar.ToolBarPlaneController
import javafx.scene.input.KeyCode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane

class GameView(private val notificationService: INotificationService = getService()) : ICustomPane {

  private fun createUpperToolBar(): Pane {
    val view = HBox()
    view.styleClass.add("tool-bar")

    val planeToolBox = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(ToolBarPlaneController())
    val planeToolBox2 = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(ToolBarPlaneController())
    val planeToolBox3 = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(ToolBarPlaneController())

    view.children.addAll(planeToolBox, planeToolBox2, planeToolBox3)

    return view
  }

  override fun getView(): Pane {
    val mainPane = AnchorPane()


    val canvas = MapCanvas()
    canvas.addViewToPane(mainPane)
    MapComponentsHandler(canvas, getService()).loadAll()

    // Add upper menu
    val upper = createUpperToolBar()
    AnchorPane.setTopAnchor(upper, 0.0)
    AnchorPane.setLeftAnchor(upper, 0.0)
    mainPane.children.add(upper)

    // Add ActionPanel
    val actionPanelView = ResourceStore.Layout.ACTION_PANEL.loadRegion(ActionPanelController())
    mainPane.children.add(actionPanelView)

    mainPane.setOnKeyPressed {
      if (it.code == KeyCode.ESCAPE) {
        notificationService.notifyEvent(GameWideEvent.EscapePressed)
      }
    }

    return mainPane
  }
}