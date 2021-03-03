package com.haouet.airproject.view

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.notification.GameWideEvent
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.util.image
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.view.actionpanel.ActionPanel
import com.haouet.airproject.view.dynamicimage.MapCanvas
import com.haouet.airproject.view.map.MapComponentsHandler
import com.haouet.airproject.view.toolbar.ToolBarPlanePm
import javafx.scene.input.KeyCode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane

class GameView(private val notificationService: INotificationService = getService()) : ICustomPane {

  private fun createUpperToolBar(): Pane {
    val view = HBox()
    view.styleClass.add("tool-bar")

    val planeToolBox = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(ToolBarPlanePm())
    val planeToolBox2 = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(ToolBarPlanePm())
    val planeToolBox3 = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(ToolBarPlanePm())

    view.children.addAll(planeToolBox, planeToolBox2, planeToolBox3)

    return view
  }

  override fun getView(): Pane {
    val mainPane = AnchorPane()

    val image = ResourceStore.Image.EARTH_MAP.getResourceStream().image()
        ?: throw IllegalStateException("Unable to load map")

    val canvas = MapCanvas(image = image)
    canvas.fitToPane(mainPane)
    mainPane.children.add(canvas)

    MapComponentsHandler(canvas, getService()).loadAll()

    // Add upper menu
    val upper = createUpperToolBar()
    AnchorPane.setTopAnchor(upper, 0.0)
    AnchorPane.setLeftAnchor(upper, 0.0)
    mainPane.children.add(upper)

    // Add ActionPanel
    ActionPanel().addTo(mainPane)

    mainPane.setOnKeyPressed {
      if (it.code == KeyCode.ESCAPE) {
        notificationService.notifyEvent(GameWideEvent.EscapePressed)
      }
    }

    return mainPane
  }
}