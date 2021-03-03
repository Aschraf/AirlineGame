package com.haouet.airproject.data.store.airport

import com.haouet.airproject.common.GcsCoordinates
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.data.store.ILocalStore
import com.haouet.airproject.share.util.FileUtil

interface IAirportStore : ILocalStore<AirportPojo>

class AirportStore : IAirportStore {
  override val content: List<AirportPojo> = FileUtil.loadCsvFile(ResourceStore.Map.AIRPORT.path, 4) {
    AirportPojo(it[0], it[1], GcsCoordinates(it[2].toDouble(), it[3].toDouble()))
  }
}