package com.airproject.store.airport

import com.airproject.LocalResource
import com.airproject.store.ILocalStore
import com.airproject.util.FileUtil


class AirportStore : ILocalStore<AirportPojo> {
  override val content: List<AirportPojo> = loadStore()


  private fun loadStore() = FileUtil.loadCsvFile(LocalResource.Map.AIRPORT, 4) {
    AirportPojo(it[0], it[1], it[2].toDouble(), it[3].toDouble())
  }
}