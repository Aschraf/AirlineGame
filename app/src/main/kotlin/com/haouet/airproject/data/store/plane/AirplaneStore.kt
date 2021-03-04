package com.haouet.airproject.data.store.plane

import com.haouet.airproject.data.PackageLoader
import com.haouet.airproject.data.PackageResource
import com.haouet.airproject.data.store.ILocalStore
import com.haouet.airproject.share.util.loadCsvFile


interface IAirplaneStore : ILocalStore<AirplanePojo>

class AirplaneStore(packageLoader: PackageLoader) : IAirplaneStore {
  override val content: List<AirplanePojo> = packageLoader.csvFile(PackageResource.AIRPLANE).loadCsvFile(9) {
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
}