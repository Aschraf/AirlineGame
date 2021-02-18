package com.airproject.event

import com.airproject.util.logger
import org.slf4j.Logger


interface INotificationService {
  fun addListener(notificationListener: INotificationListener)
  fun notifyEvent(event: IEvent)

}

class NotificationService : INotificationService {
  private val logger: Logger = logger()

  private val listeners = mutableListOf<INotificationListener>()

  override fun addListener(notificationListener: INotificationListener) {
    listeners += notificationListener
  }

  override fun notifyEvent(event: IEvent) {
    logger.debug("Notification $event")
    listeners.forEach { it.handle(event) }
  }
}

interface IEvent
