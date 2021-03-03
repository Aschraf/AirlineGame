package com.haouet.airproject.view.toolbar

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.ImageView

class ToolBarPlaneController {
  @FXML private lateinit var planesImageView: ImageView
  @FXML private lateinit var planesLabel: Label

  @FXML private lateinit var maintenanceView: ImageView
  @FXML private lateinit var maintenanceLabel: Label

  private var alreadyInit = false

  @FXML
  fun initialize() {
    println("Controller OK")
    alreadyInit = true
    planesLabel.text = "0 / 0"
    maintenanceLabel.text = "100 %"
  }

}