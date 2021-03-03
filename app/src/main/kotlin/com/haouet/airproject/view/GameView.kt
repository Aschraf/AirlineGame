package com.haouet.airproject.view

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.notification.GameWideEvent
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.view.actionpanel.ActionPanel
import com.haouet.airproject.view.dynamicimage.MapCanvas
import com.haouet.airproject.view.map.MapComponentsHandler
import com.haouet.airproject.view.toolbar.ToolBarPlanePm
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane

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
    val stackPane = StackPane()
    val anchorPane = AnchorPane()

    val image = ResourceStore.Image.EARTH_MAP.getResourceStream()?.let { Image(it) }
        ?: throw IllegalStateException("Unable to load map")

    val canvas = MapCanvas(parent = stackPane, image = image)

    MapComponentsHandler(canvas, getService()).loadAll()

    // Add upper menu
    val upper = createUpperToolBar()
    AnchorPane.setTopAnchor(upper, 0.0)
    AnchorPane.setLeftAnchor(upper, 0.0)
    anchorPane.children.add(upper)

    // Add ActionPanel
    ActionPanel().addTo(anchorPane)

    stackPane.setOnKeyPressed {
      if (it.code == KeyCode.ESCAPE) {
        notificationService.notifyEvent(GameWideEvent.EscapePressed)
      }
    }

    // Add the map
    stackPane.children.addAll(canvas, anchorPane)
    return stackPane
  }
}