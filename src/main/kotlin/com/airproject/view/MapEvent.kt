package com.airproject.view

import com.airproject.event.IEvent

sealed class MapEvent: IEvent {
  object MapLeftClick : MapEvent()
}