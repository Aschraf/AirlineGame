package com.haouet.airproject.notification

fun interface INotificationListener{
  fun handle(event: IEvent)
}