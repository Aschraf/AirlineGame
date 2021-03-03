package com.haouet.airproject.view.actionpanel

import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.view.actionpanel.actions.BuyPlaneController
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