package com.airproject.notification

fun interface INotificationListener{
  fun handle(event: IEvent)
}