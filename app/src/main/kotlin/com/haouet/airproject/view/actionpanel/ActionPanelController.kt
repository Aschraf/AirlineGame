package com.haouet.airproject.view.actionpanel

import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.view.actionpanel.actions.BuyPlaneController
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.layout.Pane

class ActionPanelController(private val close: (Node) -> Unit) {

  @FXML lateinit var mainPane: Pane
  @FXML lateinit var tabPane: TabPane
  @FXML lateinit var closeButton: Button


  @FXML
  fun initialize() {
    closeButton.setOnAction { close(mainPane) }

    val buyPlaneTab = Tab("Buy")
    buyPlaneTab.content = ResourceStore.Layout.BUY_PLANE_LAYOUT.loadRegion(BuyPlaneController())

    val dummyTab = Tab("Dummy")

    tabPane.tabs.addAll(buyPlaneTab, dummyTab)
  }
}