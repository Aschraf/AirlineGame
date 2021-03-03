package com.airproject.fxcomponent

import com.airproject.common.ResourceStore
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Region


fun ResourceStore.Layout.loadRegion(controller: Any? = null): Region {
  val loader = FXMLLoader(url())
  if (controller != null) loader.setController(controller)   // Don't erase existing controller
  return loader.load()
}


fun ResourceStore.Layout.loadScene(controller: Any? = null): Scene {
val scene = Scene(loadRegion(controller))
  scene.stylesheets.add(ResourceStore.stylesheet)
  return scene
}

