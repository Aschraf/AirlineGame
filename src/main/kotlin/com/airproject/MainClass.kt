package com.airproject

import com.airproject.binding.ApplicationBindings
import com.airproject.common.ResourceStore
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Region
import javafx.stage.Stage


class MainClass : Application() {
  override fun start(stage: Stage) {
    stage.title = "Hello World!"

    ApplicationBindings.createBinding()


    val region: Region = FXMLLoader.load(ResourceStore.Layout.MAIN_VIEW.url())

    //Creating a scene object
    val scene = Scene(region, 600.0, 400.0)
    scene.stylesheets.add(ResourceStore.stylesheet)

    //Adding scene to the stage
    stage.scene = scene

    stage.show()
  }
}


fun main() {
  Application.launch(MainClass::class.java)
}