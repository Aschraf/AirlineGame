package com.haouet.airproject

import com.haouet.airproject.binding.ApplicationBindings
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.view.GameView
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


class MainClass : Application() {
  override fun start(stage: Stage) {
    stage.title = "Hello World!"

    ApplicationBindings.createBinding()


//    val region: Region = FXMLLoader.load(ResourceStore.Layout.MAIN_VIEW.url())

    val region = GameView().getView()
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