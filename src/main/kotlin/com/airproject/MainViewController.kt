package com.airproject

import com.airproject.dynamicimage.MapCanvas
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

    val image = javaClass.classLoader.getResourceAsStream(LocalResource.Image.EARTH_MAP)?.let { Image(it) }
        ?: throw IllegalStateException("Unable to load map")

    val canvas = MapCanvas(mainPane, image)




    mainPane.children.add(canvas)
  }
}