package com.airproject.gameview

import com.airproject.binding.getService
import com.airproject.common.ResourceStore
import com.airproject.event.GameWideEvent
import com.airproject.event.INotificationService
import com.airproject.fxcomponent.imageButton
import com.airproject.fxcomponent.setBackgroundColor
import com.airproject.gameview.actionpanel.ActionPanel
import com.airproject.view.GameMap
import com.airproject.view.dynamicimage.MapCanvas
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane

class GameViewController {
  @FXML private lateinit var mainPane: StackPane
  @FXML private lateinit var mapPane: Pane
  @FXML private lateinit var flagButton: Button
  @FXML private lateinit var upperBar: HBox
  @FXML private lateinit var anchorPane: AnchorPane
  private lateinit var gameMenu: ActionPanel

  private val notificationService:INotificationService  = getService()

  @FXML
  fun initialize() {
    val image = ResourceStore.Image.EARTH_MAP.getResourceStream()?.let { Image(it) }
        ?: throw IllegalStateException("Unable to load map")

    val canvas = MapCanvas(parent = mapPane, image = image)

    GameMap(canvas, getService()).loadAll()


    flagButton.imageButton(Image(ResourceStore.Image.AIRLINE_FLAG.getResourceStream()), 200.0, 100.0)

    upperBar.setBackgroundColor("#FFFFFF")
    upperBar.style += "-fx-border-color: #D8D8D8; -fx-border-width: 1px;"

    mapPane.children.add(canvas)

    gameMenu = ActionPanel(anchorPane)


    mainPane.setOnKeyPressed {
      if (it.code == KeyCode.ESCAPE) {
        notificationService.notifyEvent(GameWideEvent.EscapePressed)
      }
    }
  }

  @FXML
  fun planeButtonClicked() {
    gameMenu.open()
  }

}