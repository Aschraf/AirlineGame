package com.haouet.airproject.view.map

import com.haouet.airproject.notification.IEvent

sealed class MapEvent: IEvent {
  object MapLeftClick : MapEvent()
}