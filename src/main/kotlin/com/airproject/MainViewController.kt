package com.airproject

import com.airproject.dynamicimage.DynamicImageView
import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane

class MainViewController {
  @FXML private lateinit var mainGrid: GridPane
  @FXML private lateinit var mainPane: Pane

  @FXML
  fun initialize() {
    println("Initializing main view $mainPane")

    mainGrid.style = "-fx-background-color:red";
    mainPane.style = "-fx-background-color:blue";

    val image = javaClass.classLoader.getResourceAsStream("image/grid.png")?.let { Image(it) }
        ?: throw IllegalStateException("Unable to load map")

    val imageComponent = DynamicImageView(image).component

    imageComponent.fitWidthProperty().bind(mainPane.widthProperty())
    imageComponent.fitHeightProperty().bind(mainPane.heightProperty())

    mainPane.children.add(imageComponent)
  }
}