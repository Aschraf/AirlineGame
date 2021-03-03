package com.haouet.airproject.view

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.view.dynamicimage.MapCanvas
import com.haouet.airproject.view.map.MapComponentsHandler
import com.haouet.airproject.view.toolbar.createUpperToolBar
import javafx.scene.image.Image
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane

class GameView : ICustomPane {

  private val stackPane = StackPane()
  private val anchorPane = AnchorPane()

  init {

    val image = ResourceStore.Image.EARTH_MAP.getResourceStream()?.let { Image(it) }
        ?: throw IllegalStateException("Unable to load map")

    val canvas = MapCanvas(parent = stackPane, image = image)

    MapComponentsHandler(canvas, getService()).loadAll()

    // Add upper menu
    val upper = createUpperToolBar()
    AnchorPane.setTopAnchor(upper, 0.0)
    AnchorPane.setLeftAnchor(upper, 0.0)
    anchorPane.children.add(upper)


    // Add the map
    stackPane.children.addAll(canvas, anchorPane)
  }

  override fun getView(): Pane = stackPane
}