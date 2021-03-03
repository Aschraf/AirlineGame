package com.haouet.airproject

import com.haouet.airproject.binding.ApplicationBindings
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.view.GameView
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


class MainClass : Application() {
  override fun start(stage: Stage) {
    stage.title = "Hello World!"

    ApplicationBindings.createBinding()

    SvgImageLoaderFactory.install()


    val region = GameView().getView()
    //Creating a scene object
    val scene = Scene(region, 800.0, 400.0)
    scene.stylesheets.add(ResourceStore.stylesheet)

    stage.minWidth = 800.0
    stage.minHeight = 400.0

    //Adding scene to the stage
    stage.scene = scene
//    stage.isMaximized = true
    stage.show()
  }
}


fun main() {
  Application.launch(MainClass::class.java)
}