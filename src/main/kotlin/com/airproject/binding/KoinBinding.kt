package com.airproject.binding

import com.airproject.data.store.airport.AirportStore
import com.airproject.data.store.airport.IAirportStore
import com.airproject.event.INotificationService
import com.airproject.event.NotificationService
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.reflect.KClass

object ApplicationBindings {
  lateinit var application: KoinApplication
  fun createBinding() {
    application = startKoin {
      // declare modules
      modules(
          module {
            // Services
            single<INotificationService> { NotificationService() }

            // Stores
            single<IAirportStore> { AirportStore() }
          }
      )

    }
  }

  fun <T : Any> get(type: KClass<T>): T = application.koin.get(type)
}

inline fun <reified T : Any> getService(): T = ApplicationBindings.get(T::class)


