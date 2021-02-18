package com.airproject.gameview

import com.airproject.event.IEvent

sealed class MenuActionEvent : IEvent {
  object ShowPlanes : MenuActionEvent()

}