package com.airproject.binding

import com.airproject.data.store.airport.AirportStore
import com.airproject.data.store.airport.IAirportStore
import com.airproject.data.store.manufacturer.IManufacturerPlaneStore
import com.airproject.data.store.manufacturer.IManufacturerStore
import com.airproject.data.store.manufacturer.ManufacturerPlaneStore
import com.airproject.data.store.manufacturer.ManufacturerStore
import com.airproject.data.store.plane.AirplaneStore
import com.airproject.data.store.plane.IAirplaneStore
import com.airproject.notification.INotificationService
import com.airproject.notification.NotificationService
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
            single<IAirplaneStore> { AirplaneStore() }
            single<IManufacturerStore> { ManufacturerStore() }
            single<IManufacturerPlaneStore> { ManufacturerPlaneStore(get(), get()) }
          }
      )

    }
  }

  fun <T : Any> get(type: KClass<T>): T = application.koin.get(type)
}

inline fun <reified T : Any> getService(): T = ApplicationBindings.get(T::class)


