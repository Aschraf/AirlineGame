package com.airproject.gameview

import com.jfoenix.controls.JFXListView
import javafx.collections.FXCollections
import javafx.fxml.FXML

class BuyPlaneView {
  @FXML private lateinit var mainList: JFXListView<String>
  @FXML private lateinit var childList: JFXListView<String>

  @FXML
  fun initialize() {
    mainList.items = FXCollections.observableList(listOf("Airbus", "Boeing"))
    childList.items = FXCollections.observableList(listOf("A320", "A330"))
  }

}