package com.haouet.airproject.notification

import com.haouet.airproject.data.store.airport.AirportPojo

sealed class ToolBoxPressedEvent : IEvent {
  object AirplaneToolBox : ToolBoxPressedEvent()
}

sealed class SystemWideEvent : IEvent {
  object EscapePressed : SystemWideEvent()
}


sealed class MapEvent : IEvent {
  object MapLeftClick : MapEvent()
  data class AirportSelected(val primaryAirport: AirportPojo?, val secondaryAirport: AirportPojo?) : MapEvent()
}
