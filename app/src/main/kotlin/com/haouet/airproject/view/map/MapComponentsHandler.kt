package com.haouet.airproject.view.map

import com.haouet.airproject.binding.getService
import com.haouet.airproject.data.store.airport.AirportPojo
import com.haouet.airproject.data.store.airport.IAirportStore
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.MapEvent
import com.haouet.airproject.util.setOnPrimaryMouseClicked
import com.haouet.airproject.view.dynamicimage.MapCanvas
import com.haouet.airproject.view.dynamicimage.MapComponent
import javafx.scene.Node
import javafx.scene.control.Tooltip
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.util.Duration

data class AirportWithComponent(
    val airportPojo: AirportPojo,
    val component: MapComponent<Circle>,
) {

  var isSelected: Boolean = false
    set(value) {
      field = value
      updateView()
    }

  private fun updateView() {
    component.node.fill = if (isSelected) Color.BLUE else Color.BLACK
  }

}

@Suppress("MagicNumber")
class MapComponentsHandler(
    private val canvas: MapCanvas,
    private val airportStore: IAirportStore,
    private val notificationService: INotificationService = getService(),
) {

  private val airportComponents = mutableListOf<AirportWithComponent>()

  fun loadAll() {
    val mapSize = canvas.imageSize

    airportStore.content.forEach {

      val node = Circle(5.0, Color.BLACK)
      node.addTooltip(it)

      val (locX, locY) = it.gcs.asPlanar(mapSize.width, mapSize.height)
      val mapComponent = MapComponent(node, locX.toInt(), locY.toInt())
      val airportComponent = AirportWithComponent(it, mapComponent)

      node.setOnPrimaryMouseClicked { e ->
        updateSelection(airportComponent)
        e.consume()
        notificationService.notifyEvent(MapEvent.AirportSelected(it))
      }


      airportComponents.add(airportComponent)
      canvas.addComponent(mapComponent)
    }




    canvas.setOnPrimaryMouseClicked {
      updateSelection(null)
      notificationService.notifyEvent(MapEvent.MapLeftClick)
    }
  }


  private fun updateSelection(component: AirportWithComponent?) {
    airportComponents.forEach { it.isSelected = false }
    component?.isSelected = true
  }

  private fun Node.addTooltip(airportPojo: AirportPojo) {
    val t = Tooltip(airportPojo.name)
    t.showDelay = Duration(500.0)
    Tooltip.install(this, t)
  }

}