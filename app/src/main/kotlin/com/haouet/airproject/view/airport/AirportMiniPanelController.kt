package com.haouet.airproject.view.airport

import com.haouet.airproject.data.store.airport.AirportPojo
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView


class AirportMiniPanelController(private val airportPojo: AirportPojo, private val closing: (Node) -> Unit) {
  @FXML lateinit var panelBox: Node
  @FXML private lateinit var closeButton: Button
  @FXML private lateinit var airportNameLabel: Label
  @FXML private lateinit var airportImageView: ImageView
  @FXML private lateinit var airportActivityLabel: Label

  @FXML
  fun initialize() {
    airportActivityLabel.text = "100"
    airportNameLabel.text = airportPojo.name
    airportImageView.image = airportPojo.loadImage()

    closeButton.setOnAction { closing(panelBox) }
  }


}