package com.airproject

import com.airproject.dynamicimage.DynamicImageView
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage




class MainClass : Application() {
  override fun start(stage: Stage) {
    stage.title = "Hello, World!"

    val image = javaClass.classLoader.getResourceAsStream("image/earth_map.jpg")?.let { Image(it) }
        ?: throw IllegalStateException("Unable to load map")

    val dynamicImage = DynamicImageView(image)

    //Creating a scene object
    val scene = Scene(dynamicImage.component, 600.0, 400.0)

    //Adding scene to the stage
    stage.scene = scene

    stage.show()
  }
}


fun main(args: Array<String>) {
  Application.launch(MainClass::class.java)
}