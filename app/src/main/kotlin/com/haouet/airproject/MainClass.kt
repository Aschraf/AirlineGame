package com.haouet.airproject

import com.haouet.airproject.binding.ApplicationBindings
import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.data.PackageLoader
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.SystemWideEvent
import com.haouet.airproject.view.GameView
import com.haouet.airproject.view.ILeftPopViewHandler
import com.haouet.airproject.view.LeftPopViewHandler
import com.haouet.airproject.view.airport.AirportMiniPanelDisplayService
import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.koin.dsl.module


class MainClass : Application() {
  override fun start(stage: Stage) {
    stage.title = "Hello World!"

    val packageLoader = PackageLoader("data")


    SvgImageLoaderFactory.install()

    val gameView = GameView()
    val gamePane = gameView.mainPane

    val viewModule = createViewModule(gamePane)
    ApplicationBindings.createBinding(viewModule, packageLoader)

    val notificationService: INotificationService = getService()

    AirportMiniPanelDisplayService.start()

    gamePane.setOnKeyPressed {
      if (it.code == KeyCode.ESCAPE) {
        notificationService.notifyEvent(SystemWideEvent.EscapePressed)
      }
    }

    //Creating a scene object
    val scene = Scene(gamePane, MIN_WIDTH, MIN_HEIGHT)
    scene.stylesheets.add(ResourceStore.stylesheet)

    stage.minWidth = MIN_WIDTH
    stage.minHeight = MIN_HEIGHT

    gameView.loadView()

    //Adding scene to the stage
    stage.scene = scene
    stage.isMaximized = true
    stage.show()
  }


  private fun createViewModule(pane: Pane) = module {
    single<ILeftPopViewHandler> { LeftPopViewHandler(pane, get()) }
  }

  companion object {
    const val MIN_WIDTH = 800.0
    const val MIN_HEIGHT = 400.0
  }
}


fun main() {
  Application.launch(MainClass::class.java)
}