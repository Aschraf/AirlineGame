package com.airproject.event

fun interface INotificationListener{
  fun handle(event: IEvent)
}