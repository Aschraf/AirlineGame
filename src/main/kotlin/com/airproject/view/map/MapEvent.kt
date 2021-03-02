package com.airproject.view.map

import com.airproject.notification.IEvent

sealed class MapEvent: IEvent {
  object MapLeftClick : MapEvent()
}