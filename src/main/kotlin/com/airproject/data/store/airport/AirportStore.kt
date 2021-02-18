package com.airproject.data.store.airport

import com.airproject.common.GcsCoordinates
import com.airproject.common.LocalResource
import com.airproject.data.store.ILocalStore
import com.airproject.util.FileUtil

interface IAirportStore : ILocalStore<AirportPojo>

class AirportStore : IAirportStore {
  override val content: List<AirportPojo> = FileUtil.loadCsvFile(LocalResource.Map.AIRPORT, 4) {
    AirportPojo(it[0], it[1], GcsCoordinates(it[2].toDouble(), it[3].toDouble()))
  }
}