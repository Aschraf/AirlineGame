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


enum class Selection {
  SELECT_1,
  SELECT_2,
  NOT_SELECTED,
}

data class AirportWithComponent(
    val airportPojo: AirportPojo,
    val component: MapComponent<Circle>,
) {

  var selection: Selection = Selection.NOT_SELECTED
    set(value) {
      field = value
      updateView()
    }

  private fun updateView() {
    component.node.fill = when (selection) {
      Selection.SELECT_1     -> Color.BLUE
      Selection.SELECT_2     -> Color.GREEN
      Selection.NOT_SELECTED -> Color.BLACK
    }
  }

}

@Suppress("MagicNumber")
class MapComponentsHandler(
    private val canvas: MapCanvas,
    private val airportStore: IAirportStore,
    private val notificationService: INotificationService = getService(),
) {

  private val airportComponents = mutableListOf<AirportWithComponent>()
  private var primaryAirport: AirportWithComponent? = null
  private var secondaryAirport: AirportWithComponent? = null

  fun loadAll() {
    val mapSize = canvas.imageSize

    airportStore.content.forEach {

      val node = Circle(5.0, Color.BLACK)
      node.addTooltip(it)

      val (locX, locY) = it.gcs.asPlanar(mapSize.width, mapSize.height)
      val mapComponent = MapComponent(node, locX.toInt(), locY.toInt())
      val airportComponent = AirportWithComponent(it, mapComponent)

      node.setOnPrimaryMouseClicked { e ->
        updateSelection(airportComponent, e.isControlDown)
        notificationService.notifyEvent(MapEvent.AirportSelected(primaryAirport?.airportPojo, secondaryAirport?.airportPojo))
        e.consume()
      }

      airportComponents.add(airportComponent)
      canvas.addComponent(mapComponent)
    }


    canvas.setOnPrimaryMouseClicked {
      if (primaryAirport != null || secondaryAirport != null) {
        clearSelection()
        notificationService.notifyEvent(MapEvent.AirportSelected(primaryAirport?.airportPojo, secondaryAirport?.airportPojo))
      }

      notificationService.notifyEvent(MapEvent.MapLeftClick)
    }
  }


  private fun updateSelection(component: AirportWithComponent, isCtrlDown: Boolean) {
    if (primaryAirport != null && isCtrlDown) {
      // Update secondary Airport
      secondaryAirport?.selection = Selection.NOT_SELECTED
      component.selection = Selection.SELECT_2
      secondaryAirport = component
    } else {
      // Update primary Airport
      secondaryAirport?.selection = Selection.NOT_SELECTED
      secondaryAirport = null
      primaryAirport?.selection = Selection.NOT_SELECTED
      component.selection = Selection.SELECT_1
      primaryAirport = component
    }
  }

  private fun clearSelection() {
    primaryAirport?.selection = Selection.NOT_SELECTED
    primaryAirport = null
    secondaryAirport?.selection = Selection.NOT_SELECTED
    secondaryAirport = null
  }

}


private fun Node.addTooltip(airportPojo: AirportPojo) {
  val t = Tooltip(airportPojo.name)
  t.showDelay = Duration(500.0)
  Tooltip.install(this, t)
}