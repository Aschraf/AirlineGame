package com.haouet.airproject.notification

import com.haouet.airproject.util.logger
import org.slf4j.Logger
import kotlin.reflect.KClass


interface INotificationService {
  /** Listens to all events. */
  fun addGeneralListener(notificationListener: INotificationListener)

  /** Listens to a specific type of event */
  fun <T : IEvent> addTypeListener(type: KClass<T>, notificationListener: INotificationListener)

  fun notifyEvent(event: IEvent)
}

class NotificationService : INotificationService {
  private val logger: Logger = logger()

  private val listeners = mutableListOf<INotificationListener>()
  private val typeListeners = mutableMapOf<KClass<*>, MutableList<INotificationListener>>()


  override fun addGeneralListener(notificationListener: INotificationListener) {
    listeners += notificationListener
  }

  override fun <T : IEvent> addTypeListener(type: KClass<T>, notificationListener: INotificationListener) {
    typeListeners.getOrPut(type, { mutableListOf() }).add(notificationListener)
  }

  override fun notifyEvent(event: IEvent) {
    logger.debug("Notification $event")
    listeners.forEach { it.handle(event) }

    typeListeners.filterKeys { it.java.isAssignableFrom(event::class.java) }
        .flatMap { it.value }
        .forEach { it.handle(event) }
  }



}

interface IEvent
