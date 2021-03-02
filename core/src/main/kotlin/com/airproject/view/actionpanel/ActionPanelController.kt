package com.airproject.view.actionpanel

import com.airproject.common.ResourceStore
import com.airproject.fxcomponent.loadRegion
import com.airproject.view.actionpanel.actions.BuyPlaneController
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