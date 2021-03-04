package com.haouet.airproject.data.store.airport

import com.haouet.airproject.common.GcsCoordinates
import com.haouet.airproject.data.PackageLoader
import com.haouet.airproject.data.PackageResource
import com.haouet.airproject.data.store.ILocalStore
import com.haouet.airproject.share.util.loadCsvFile

interface IAirportStore : ILocalStore<AirportPojo>

class AirportStore(packageLoader: PackageLoader) : IAirportStore {
  override val content: List<AirportPojo> = packageLoader.csvFile(PackageResource.AIRPORT).loadCsvFile(4) {
    AirportPojo(it[0], it[1], GcsCoordinates(it[2].toDouble(), it[3].toDouble()))
  }
}