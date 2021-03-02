package com.airproject.gameview.actionpanel

import com.airproject.common.ResourceStore
import com.airproject.fxcomponent.loadRegion
import com.airproject.gameview.BuyPlaneController
import javafx.fxml.FXML
import javafx.scene.control.Tab
import javafx.scene.control.TabPane

class ActionPanelController {
  @FXML lateinit var tabPane: TabPane

  @FXML
  fun initialize() {
    val buyPlaneTab = Tab("Buy")
    buyPlaneTab.content = ResourceStore.Layout.BUY_PLANE_LAYOUT.loadRegion(BuyPlaneController())

    tabPane.tabs.add(buyPlaneTab)
  }
}