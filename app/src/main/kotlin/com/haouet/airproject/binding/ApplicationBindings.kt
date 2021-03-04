package com.haouet.airproject.binding

import com.haouet.airproject.data.PackageLoader
import com.haouet.airproject.data.store.airport.AirportStore
import com.haouet.airproject.data.store.airport.IAirportStore
import com.haouet.airproject.data.store.plane.AirplaneStore
import com.haouet.airproject.data.store.plane.IAirplaneStore
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.NotificationService
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.reflect.KClass

object ApplicationBindings {
  lateinit var application: KoinApplication
  fun createBinding(extraModule: Module, packageLoader: PackageLoader) {
    application = startKoin {
      // declare modules
      modules(
          extraModule,

          module {
            // Services
            single<INotificationService> { NotificationService() }

            // Stores
            single<IAirportStore> { AirportStore(packageLoader) }
            single<IAirplaneStore> { AirplaneStore(packageLoader) }

            // Controllers
          }

      )

    }
  }

  fun <T : Any> get(type: KClass<T>): T = application.koin.get(type)
}

inline fun <reified T : Any> getService(): T = ApplicationBindings.get(T::class)


