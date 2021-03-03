package com.haouet.airproject.data.store.manufacturer

import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.data.store.ILocalStore
import com.haouet.airproject.share.util.FileUtil

interface IManufacturerStore : ILocalStore<ManufacturerPojo>

class ManufacturerStore: IManufacturerStore {
  override val content: List<ManufacturerPojo> = FileUtil.loadCsvFile(ResourceStore.Map.MANUFACTURER.path, 3) {
    ManufacturerPojo(it[0], it[1], it[2])
  }
}

