package com.airproject.data.store.manufacturer

import com.airproject.common.ResourceStore
import com.airproject.data.store.ILocalStore
import com.airproject.util.FileUtil

interface IManufacturerStore : ILocalStore<ManufacturerPojo>

class ManufacturerStore: IManufacturerStore {
  override val content: List<ManufacturerPojo> = FileUtil.loadCsvFile(ResourceStore.Map.MANUFACTURER.path, 2) {
    ManufacturerPojo(it[0], it[1])
  }
}

