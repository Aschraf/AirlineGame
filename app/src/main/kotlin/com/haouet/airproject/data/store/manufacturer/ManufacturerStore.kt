package com.haouet.airproject.data.store.manufacturer

import com.haouet.airproject.data.PackageLoader
import com.haouet.airproject.data.PackageResource
import com.haouet.airproject.data.store.ILocalStore
import com.haouet.airproject.share.util.loadCsvFile

interface IManufacturerStore : ILocalStore<ManufacturerPojo>

class ManufacturerStore(packageLoader: PackageLoader) : IManufacturerStore {
  override val content: List<ManufacturerPojo> = packageLoader.csvFile(PackageResource.MANUFACTURER).loadCsvFile(3) {
    ManufacturerPojo(it[0], it[1], packageLoader.imageFile(PackageResource.MANUFACTURER, it[2]))
  }
}

