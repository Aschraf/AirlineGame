package com.airproject.view

import com.airproject.data.store.airport.AirportPojo
import com.airproject.data.store.airport.AirportStore
import com.airproject.view.dynamicimage.MapCanvas
import com.airproject.view.dynamicimage.MapComponent
import javafx.scene.Node
import javafx.scene.control.Tooltip
import javafx.scene.input.MouseButton
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.util.Duration


class MapResourcesLoader(
    private val canvas: MapCanvas,
    private val airportStore: AirportStore,
) {
  fun loadAll() {
    val mapSize = canvas.imageSize

    airportStore.content.forEach {
      val (locX, locY) = it.gcs.asPlanar(mapSize.width, mapSize.height)

      println("Placing ${it.sign} in ($locX, $locY)")

      val node = Circle(5.0, Color.BLACK)

      node.addTooltip(it)
      node.setOnMouseClicked { e ->
        if (e.button == MouseButton.PRIMARY) {
          println("Clicked on ${it.sign}")
          node.fill = Color.BLUE
        }
      }

      val airportComponent = MapComponent(node, locX.toInt(), locY.toInt())

      canvas.addComponent(airportComponent)
    }
  }

  private fun Node.addTooltip(airportPojo: AirportPojo) {
    val t = Tooltip(airportPojo.name)
    t.showDelay = Duration.ZERO
    Tooltip.install(this, t)
  }


}