package com.airproject.gameview

import com.airproject.binding.getService
import com.airproject.data.store.manufacturer.IManufacturerStore
import com.airproject.data.store.manufacturer.ManufacturerPojo
import com.airproject.data.store.plane.AirplanePojo
import com.airproject.data.store.plane.IAirplaneStore
import com.airproject.fxcomponent.IconListCell
import com.jfoenix.controls.JFXListView
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListCell
import javafx.util.Callback

class BuyPlaneController {
  @FXML private lateinit var mainList: JFXListView<ManufacturerPojo>
  @FXML private lateinit var childList: JFXListView<AirplanePojo>

  @FXML
  fun initialize() {
    fillList()
  }

  private fun fillList() {
    val manufacturer = getService<IManufacturerStore>().content
    mainList.items = FXCollections.observableList(manufacturer)


    val planes = getService<IAirplaneStore>().content
    childList.items = FXCollections.observableList(planes)

    mainList.cellFactory = Callback { ManufacturerCellStyleFactory() }
    childList.cellFactory = Callback { PlaneCellStyleFactory() }

  }
}

class ManufacturerCellStyleFactory : ListCell<ManufacturerPojo>() {
  override fun updateItem(item: ManufacturerPojo?, isEmpty: Boolean) {
    super.updateItem(item, isEmpty)

    graphic = item?.let {
      IconListCell(text = it.id)
    }
  }
}

class PlaneCellStyleFactory : ListCell<AirplanePojo>() {
  override fun updateItem(item: AirplanePojo?, isEmpty: Boolean) {
    super.updateItem(item, isEmpty)
    graphic = item?.let {
      IconListCell(text = it.modelName)
    }
  }
}