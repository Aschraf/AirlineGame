package com.haouet.airproject.view.events

import com.haouet.airproject.notification.IEvent

sealed class ToolBoxPressedEvent : IEvent {
  object AirplaneToolBox : ToolBoxPressedEvent()

}