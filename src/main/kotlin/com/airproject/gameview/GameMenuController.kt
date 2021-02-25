package com.airproject.gameview

import com.airproject.binding.getService
import com.airproject.common.ResourceStore
import com.airproject.data.store.manufacturer.ManufacturerPlaneStore
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Tab
import javafx.scene.control.TabPane

class GameMenuController {
  @FXML lateinit var tabPane: TabPane

  @FXML
  fun initialize() {


    val buyPlaneTab = Tab("Buy")
    val loader = FXMLLoader(ResourceStore.Layout.BUY_PLANE_LAYOUT.url())
    loader.setController(BuyPlaneController(ManufacturerPlaneStore(getService(), getService())))
    buyPlaneTab.content =loader.load()


    tabPane.tabs.add(buyPlaneTab)
  }
}