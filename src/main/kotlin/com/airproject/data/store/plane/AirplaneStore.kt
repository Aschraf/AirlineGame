package com.airproject.data.store.plane

import com.airproject.common.ResourceStore
import com.airproject.data.store.ILocalStore
import com.airproject.util.FileUtil

interface IAirplaneStore : ILocalStore<AirplanePojo>

class AirplaneStore : IAirplaneStore {
  override val content: List<AirplanePojo> = FileUtil.loadCsvFile(ResourceStore.Map.AIRPLANE.path, 6) {
    AirplanePojo(it[0], it[1], it[2].toInt(), it[3].toInt(), it[4].toInt(), it[5].toInt())
  }
}