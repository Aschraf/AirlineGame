package com.haouet.airproject.view.actionpanel.actions

import com.haouet.airproject.binding.getService
import com.haouet.airproject.data.store.manufacturer.IManufacturerPlaneStore
import com.haouet.airproject.data.store.manufacturer.ManufacturerPlanes
import com.haouet.airproject.data.store.plane.AirplanePojo
import com.haouet.airproject.share.util.NumberFormatter
import com.haouet.airproject.share.view.IconListCell
import com.jfoenix.controls.JFXListView
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.util.Callback


class BuyPlaneController(
    private val manufacturerPlaneStore: IManufacturerPlaneStore = getService(),
) {
  @FXML
  private lateinit var mainList: JFXListView<ManufacturerPlanes>

  @FXML
  private lateinit var childList: JFXListView<AirplanePojo>

  // Details
  @FXML
  private lateinit var planeDetailPane: Pane

  @FXML
  private lateinit var planeNameLabel: Label

  @FXML
  private lateinit var planeImageView: ImageView

  @FXML
  private lateinit var typeLabel: Label

  @FXML
  private lateinit var priceLabel: Label

  @FXML
  private lateinit var rangeLabel: Label

  @FXML
  private lateinit var speedLabel: Label

  @FXML
  private lateinit var seatsLabel: Label

  @FXML
  private lateinit var consumptionLabel: Label


  @FXML
  fun initialize() {
    fillList()
  }

  private fun fillList() {

    SvgImageLoaderFactory.install()

    val content = manufacturerPlaneStore.content

    planeDetailPane.isVisible = false

    mainList.items = FXCollections.observableList(content)
    mainList.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
      childList.items = FXCollections.observableList(newValue.planes)
    }

    childList.selectionModel.selectedItemProperty().addListener { _, _, plane ->
      if (plane != null) {
        planeNameLabel.text = plane.fullName
        planeDetailPane.isVisible = true
        planeImageView.image = plane.loadImage()

        // Fill fields
        typeLabel.text = "Passenger"
        priceLabel.text = "€ " + NumberFormatter.format(plane.price)
        rangeLabel.text = plane.range.toString() + " km"
        speedLabel.text = plane.speed.toString() + " km/h"
        seatsLabel.text = plane.maxSeat.toString() + " seats"
        consumptionLabel.text = plane.consumption.toString() + " kg/km"
      } else {
        planeDetailPane.isVisible = false
      }
    }


    mainList.cellFactory = Callback { ManufacturerCellStyleFactory() }
    childList.cellFactory = Callback { PlaneCellStyleFactory() }

  }
}

class ManufacturerCellStyleFactory : ListCell<ManufacturerPlanes>() {
  override fun updateItem(item: ManufacturerPlanes?, isEmpty: Boolean) {
    super.updateItem(item, isEmpty)

    graphic = item?.let {

      IconListCell(
          leftIcon = it.manufacturerPojo.loadLogo(),
          text = it.manufacturerPojo.id
      )
    }
  }
}

class PlaneCellStyleFactory : ListCell<AirplanePojo>() {
  override fun updateItem(item: AirplanePojo?, isEmpty: Boolean) {
    super.updateItem(item, isEmpty)
    graphic = item?.let {
      IconListCell(text = it.fullName)
    }
  }
}