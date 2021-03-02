package com.airproject.data.store.manufacturer

import com.airproject.data.store.ILocalStore
import com.airproject.data.store.plane.AirplanePojo
import com.airproject.data.store.plane.IAirplaneStore

data class ManufacturerPlanes(val manufacturerPojo: ManufacturerPojo, val planes:List<AirplanePojo>)

interface IManufacturerPlaneStore  : ILocalStore<ManufacturerPlanes>

class ManufacturerPlaneStore(
  private val manufacturerStore: IManufacturerStore,
  private val planeStore: IAirplaneStore,
) : IManufacturerPlaneStore {
  override val content: List<ManufacturerPlanes>
    get() {
      val manufacturers = manufacturerStore.content

     return planeStore.content
        .groupBy { it.manufacturer }
        .mapKeys { (key, _) -> manufacturers.first { it.id == key } }
        .map { (manufacturer, planes) -> ManufacturerPlanes(manufacturer, planes)  }
    }
}

