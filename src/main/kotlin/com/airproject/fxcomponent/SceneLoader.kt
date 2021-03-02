package com.airproject.fxcomponent

import com.airproject.common.ResourceStore
import javafx.fxml.FXMLLoader
import javafx.scene.Scene

fun ResourceStore.Layout.load(controller: Any): Scene {
  val loader = FXMLLoader(url())
  loader.setController(controller)
  val scene = Scene(loader.load())
  scene.stylesheets.add(ResourceStore.stylesheet)
  return scene
}