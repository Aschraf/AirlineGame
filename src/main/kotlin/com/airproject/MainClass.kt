package com.airproject

import com.airproject.binding.ApplicationBindings
import com.airproject.binding.getService
import com.airproject.common.LocalResource
import com.airproject.event.INotificationService
import com.airproject.gameview.MenuActionEvent
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Region
import javafx.stage.Stage


class MainClass : Application() {
  override fun start(stage: Stage) {
    stage.title = "Hello World!"

    ApplicationBindings.createBinding()

    val fxmlLoader = FXMLLoader(javaClass.getResource(LocalResource.Layout.MAIN_VIEW))
    val region: Region = fxmlLoader.load()

    //Creating a scene object
    val scene = Scene(region, 600.0, 400.0)

    //Adding scene to the stage
    stage.scene = scene

    stage.show()


    getService<INotificationService>().addListener { event ->
      if (event == MenuActionEvent.ShowPlanes) {
        println("Main show plane!")

      }
    }
  }
}


fun main() {
  Application.launch(MainClass::class.java)
}