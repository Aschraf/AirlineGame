package com.airproject.gameview

import com.airproject.binding.getService
import com.airproject.common.LocalResource
import com.airproject.data.store.airport.AirportStore
import com.airproject.event.INotificationService
import com.airproject.fxcomponent.imageButton
import com.airproject.fxcomponent.setBackgroundColor
import com.airproject.view.GameMap
import com.airproject.view.dynamicimage.MapCanvas
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane

class GameViewController  {
  @FXML private lateinit var mainGrid: GridPane
  @FXML private lateinit var mainPane: Pane
  @FXML private lateinit var flagButton: Button
  @FXML private lateinit var topListView: ListView<String>
  @FXML private lateinit var upperBar: HBox


  @FXML
  fun initialize() {
    println("Initializing main view $mainPane")

    val image = javaClass.classLoader.getResourceAsStream(LocalResource.Image.EARTH_MAP)?.let { Image(it) }
        ?: throw IllegalStateException("Unable to load map")

    val canvas = MapCanvas(mainPane, image)


    val airportStore = AirportStore()
    GameMap(canvas, airportStore).loadAll()


    flagButton.imageButton(Image(javaClass.classLoader.getResourceAsStream(LocalResource.Image.AIRLINE_FLAG)), 200.0, 100.0)

    upperBar.setBackgroundColor("#FFFFFF")
    upperBar.style += "-fx-border-color: #D8D8D8; -fx-border-width: 1px;"

    mainPane.children.add(canvas)
  }

  @FXML
  fun planeButtonClicked() {
    val notificationService:INotificationService = getService()
    println("Plane button clicked $notificationService")

    notificationService.notifyEvent(MenuActionEvent.ShowPlanes)

    val fxmlLoader = FXMLLoader(javaClass.getResource(LocalResource.Layout.PLANE_MAIN))


  }

}