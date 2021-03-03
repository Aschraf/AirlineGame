package com.haouet.airproject.view.toolbar

import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.util.loadRegion
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane


fun createUpperToolBar(): Pane {
  val view = HBox()
  view.styleClass.add("tool-bar")

  val controller = ToolBarPlaneController()
  val planeToolBox = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(controller)
  val planeToolBox2 = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(controller)
  val planeToolBox3 = ResourceStore.Layout.TOOL_BAR_PLANES.loadRegion(controller)

  view.children.addAll(planeToolBox, planeToolBox2, planeToolBox3)

  return view
}