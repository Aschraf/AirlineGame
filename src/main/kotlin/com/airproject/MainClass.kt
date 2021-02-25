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

//    val desktopPane = DesktopPane()
//    val newWindow = Button("New Window")
//    var count = 0
//    newWindow.setOnAction { e ->
//      val window = InternalWindow(
//          "window-$count",
//          FontIcon("mdi-application:20"),
//          "Title " + count++,
//          Label("Content")
//      )
//      desktopPane.addInternalWindow(window)
//    }
//
//    val mainPane = BorderPane()
//    mainPane.setPrefSize(800.0, 600.0)
//    mainPane.top = newWindow
//    mainPane.center = desktopPane
//
//    stage.scene = Scene(mainPane)
//    stage.show()

    val region: Region = FXMLLoader.load(ResourceStore.Layout.MAIN_VIEW.url())

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