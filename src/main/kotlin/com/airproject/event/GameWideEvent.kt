package com.airproject.event


sealed class GameWideEvent : IEvent {
  object EscapePressed : GameWideEvent()
}

