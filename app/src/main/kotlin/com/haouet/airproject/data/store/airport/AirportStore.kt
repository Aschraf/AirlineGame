package com.haouet.airproject.data.store.airport

import com.haouet.airproject.common.GcsCoordinates
import com.haouet.airproject.data.PackageLoader
import com.haouet.airproject.data.PackageResource
import com.haouet.airproject.data.store.ILocalStore
import com.haouet.airproject.share.util.loadCsvFile

interface IAirportStore : ILocalStore<AirportPojo>

class AirportStore(packageLoader: PackageLoader) : IAirportStore {
  override val content: List<AirportPojo> = packageLoader.csvFile(PackageResource.AIRPORT).loadCsvFile(5) {
    AirportPojo(
        it[0].trim(),
        it[1].trim(),
        packageLoader.imageFile(PackageResource.AIRPORT, it[2]),
        GcsCoordinates(it[3].toDouble(), it[4].toDouble())
    )
  }
}