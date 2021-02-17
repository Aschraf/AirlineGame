package com.airproject

import com.airproject.common.LocalResource
import com.airproject.data.store.airport.AirportStore
import com.airproject.view.MapResourcesLoader
import com.airproject.view.dynamicimage.MapCanvas
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



    val airportStore = AirportStore()
    MapResourcesLoader(canvas, airportStore).loadAll()


    mainPane.children.add(canvas)
  }
}