package com.haouet.airproject.view.airport

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.MapEvent
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.util.logger
import com.haouet.airproject.view.ILeftPopViewHandler

object AirportMiniPanelDisplayService {
  private val log = logger()

  fun start(
      notificationService: INotificationService = getService(),
      leftPopViewHandler: ILeftPopViewHandler = getService(),
  ) {
    notificationService.addTypeListener(MapEvent.AirportSelected::class) { event ->

      val airportPojo = (event as MapEvent.AirportSelected).airportPojo

      val controller = AirportMiniPanelController(airportPojo) {
        leftPopViewHandler.removeNode(it)
      }

      val currentPanel = ResourceStore.Layout.AIRPORT_MINI_PANEL.loadRegion(controller)

      leftPopViewHandler.showNode(currentPanel)

      log.debug("Displayed airport panel with ${airportPojo.name}")
    }
  }

}