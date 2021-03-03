package com.haouet.airproject.notification


sealed class GameWideEvent : IEvent {
  object EscapePressed : GameWideEvent()
}

