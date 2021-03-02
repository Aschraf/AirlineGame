package com.airproject.notification


sealed class GameWideEvent : IEvent {
  object EscapePressed : GameWideEvent()
}

