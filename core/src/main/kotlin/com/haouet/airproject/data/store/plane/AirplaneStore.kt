package com.haouet.airproject.data.store.plane

import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.data.store.ILocalStore
import com.haouet.airproject.share.util.FileUtil


interface IAirplaneStore : ILocalStore<AirplanePojo>

class AirplaneStore : IAirplaneStore {
  override val content: List<AirplanePojo> = FileUtil.loadCsvFile(ResourceStore.Map.AIRPLANE.path, 9) {
    var counter = 0
    AirplanePojo(
        manufacturer = it[counter++],
        modelName = it[counter++],
        image = it[counter++],
        price = (it[counter++].toDouble() * 1_000_000).toInt(),
        launchYear = it[counter++].toInt(),
        maxSeat = it[counter++].toInt(),
        speed = it[counter++].toInt(),
        range = it[counter++].toInt(),
        consumption = it[counter].toFloat(),
    )
  }
}