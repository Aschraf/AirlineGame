package com.haouet.airproject.view.airport

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.data.store.airport.AirportPojo
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.MapEvent
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.util.logger
import com.haouet.airproject.view.ILeftPopViewHandler
import com.haouet.airproject.view.Position
import javafx.scene.Node
import javafx.scene.layout.Region

class AirportMiniPanelActionListener {
  private val log = logger()
  private var previousPrimary: AirportPojo? = null
  private var previousSecondary: AirportPojo? = null
  private var primaryNode: Node? = null
  private var secondaryNode: Node? = null


  private fun loadAndShow(leftPopViewHandler: ILeftPopViewHandler, airportPojo: AirportPojo, position: Position): Region {
    val controller = AirportMiniPanelController(airportPojo) {
      leftPopViewHandler.removeNode(it)
    }

    val currentPanel = ResourceStore.Layout.AIRPORT_MINI_PANEL.loadRegion(controller)

    leftPopViewHandler.showNode(currentPanel, position)

    log.debug("Displayed airport panel with ${airportPojo.name}")
    return currentPanel
  }


  fun start(
      notificationService: INotificationService = getService(),
      leftPopViewHandler: ILeftPopViewHandler = getService(),
  ) {
    notificationService.addTypeListener(MapEvent.AirportSelected::class) { event ->

      val airportPojo = (event as MapEvent.AirportSelected)
      val newPrimary = airportPojo.primaryAirport
      val newSecondary = airportPojo.secondaryAirport

      if (newPrimary == null) {
        primaryNode?.let { leftPopViewHandler.removeNode(it, Position.POS1) }
      } else if (newPrimary != previousPrimary) {
        primaryNode = loadAndShow(leftPopViewHandler, newPrimary, Position.POS1)
      }

      if (newSecondary == null) {
        secondaryNode?.let { leftPopViewHandler.removeNode(it, Position.POS2) }
      } else if (newSecondary != previousSecondary) {
        secondaryNode = loadAndShow(leftPopViewHandler, newSecondary, Position.POS2)
      }

      previousPrimary = newPrimary
      previousSecondary = newSecondary
    }
  }

}