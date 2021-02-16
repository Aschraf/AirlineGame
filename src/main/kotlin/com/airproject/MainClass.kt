package com.airproject

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Region
import javafx.stage.Stage


class MainClass : Application() {
  override fun start(stage: Stage) {
    stage.title = "Hello, World!"

    val fxmlLoader = FXMLLoader(javaClass.getResource("/layout/MainView.fxml"))
    val region: Region = fxmlLoader.load()


    //Creating a scene object
    val scene = Scene(region, 600.0, 400.0)

    //Adding scene to the stage
    stage.scene = scene

    stage.show()
  }
}


fun main() {
  Application.launch(MainClass::class.java)
}