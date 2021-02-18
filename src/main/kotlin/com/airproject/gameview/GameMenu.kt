package com.airproject.gameview

import com.airproject.common.LocalResource
import com.airproject.fxcomponent.setBackgroundColor
import javafx.animation.TranslateTransition
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.layout.Pane
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.util.Duration

class GameMenu(private val parentPane: Pane) {
  private var component: Region? = null

  fun open() {
    if (component != null) return

    val fxmlLoader = FXMLLoader(javaClass.getResource(LocalResource.Layout.GAME_MENU))
    component = fxmlLoader.load()
    component!!.setBackgroundColor("#FFFFFF")
    StackPane.setAlignment(component, Pos.CENTER_LEFT);


    val translateTransition = TranslateTransition(Duration(100.0), component)
    translateTransition.fromX = -component!!.prefWidth
    translateTransition.toX = 0.0
    translateTransition.play()

    parentPane.children.add(component)


  }

  fun close() {
    if (component == null) return

    val translateTransition = TranslateTransition(Duration(100.0), component)
    translateTransition.fromX = 0.0
    translateTransition.toX = -component!!.prefWidth
    translateTransition.play()

    translateTransition.setOnFinished {
      parentPane.children.remove(component)
    }

    component = null
  }
}