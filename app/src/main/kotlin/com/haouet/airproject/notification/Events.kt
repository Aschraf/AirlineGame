package com.haouet.airproject.notification

sealed class ToolBoxPressedEvent : IEvent {
  object AirplaneToolBox : ToolBoxPressedEvent()
}

sealed class SystemWideEvent : IEvent {
  object EscapePressed : SystemWideEvent()
}


sealed class MapEvent : IEvent {
  object MapLeftClick : MapEvent()
}
