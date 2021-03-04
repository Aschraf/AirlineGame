package com.haouet.airproject

import com.haouet.airproject.binding.ApplicationBindings
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.data.PackageLoader
import com.haouet.airproject.view.GameView
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


class MainClass : Application() {
  override fun start(stage: Stage) {
    stage.title = "Hello World!"

    val packageLoader = PackageLoader("data")
    ApplicationBindings.createBinding(packageLoader)

    SvgImageLoaderFactory.install()


    val region = GameView().getView()

    //Creating a scene object
    val scene = Scene(region, MIN_WIDTH, MIN_HEIGHT)
    scene.stylesheets.add(ResourceStore.stylesheet)

    stage.minWidth = MIN_WIDTH
    stage.minHeight = MIN_HEIGHT

    //Adding scene to the stage
    stage.scene = scene
    stage.isMaximized = true
    stage.show()
  }

  companion object {
    const val MIN_WIDTH = 800.0
    const val MIN_HEIGHT = 400.0
  }
}


fun main() {
  Application.launch(MainClass::class.java)
}