package com.airproject.gameview

import com.airproject.common.ResourceStore
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Tab
import javafx.scene.control.TabPane

class GameMenuController {
  @FXML lateinit var tabPane: TabPane

  @FXML
  fun initialize() {


    val buyPlaneTab = Tab("Buy")
    buyPlaneTab.content = FXMLLoader.load(ResourceStore.Layout.BUY_PLANE_LAYOUT.url())


    tabPane.tabs.add(buyPlaneTab)
  }
}