package com.haouet.airproject.data.store.plane

import com.haouet.airproject.data.PackageLoader
import com.haouet.airproject.data.PackageResource
import com.haouet.airproject.share.util.loadCsvFile


interface IAirplaneStore {
  val planes: List<AirplanePojo>
  val manufacturers: List<ManufacturerPojo>
  val manufacturerToPlane: List<ManufacturerPlanes>
}

class AirplaneStore(packageLoader: PackageLoader) : IAirplaneStore {

  override val planes: List<AirplanePojo> = packageLoader.csvFile(PackageResource.AIRPLANE).loadCsvFile(9) {
    var counter = 0
    AirplanePojo(
        manufacturer = it[counter++],
        modelName = it[counter++],
        image = packageLoader.imageFile(PackageResource.AIRPLANE, it[counter++]),
        price = (it[counter++].toDouble() * 1_000_000).toInt(),
        launchYear = it[counter++].toInt(),
        maxSeat = it[counter++].toInt(),
        speed = it[counter++].toInt(),
        range = it[counter++].toInt(),
        consumption = it[counter].toFloat(),
    )
  }

  override val manufacturers: List<ManufacturerPojo> = packageLoader.csvFile(PackageResource.MANUFACTURER).loadCsvFile(3) {
    ManufacturerPojo(it[0], it[1], packageLoader.imageFile(PackageResource.MANUFACTURER, it[2]))
  }

  override val manufacturerToPlane: List<ManufacturerPlanes> = planes
      .groupBy { it.manufacturer }
      .mapKeys { (key, _) -> manufacturers.first { it.id == key } }
      .map { (manufacturer, planes) -> ManufacturerPlanes(manufacturer, planes) }
}

