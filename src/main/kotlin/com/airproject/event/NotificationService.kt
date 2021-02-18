package com.airproject.event


interface INotificationService {
  fun addListener(notificationListener: INotificationListener)
  fun notifyEvent(event: IEvent)

}

class NotificationService : INotificationService {
  private val listeners = mutableListOf<INotificationListener>()

  override fun addListener(notificationListener: INotificationListener) {
    listeners += notificationListener
  }

  override fun notifyEvent(event: IEvent) {
    listeners.forEach { it.handle(event) }
  }
}

interface IEvent
