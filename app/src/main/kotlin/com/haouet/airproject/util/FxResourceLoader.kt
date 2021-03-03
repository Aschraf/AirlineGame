package com.haouet.airproject.util

import com.haouet.airproject.common.ResourceStore
import java.io.InputStream
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
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

/** Small convenience function */
fun InputStream?.image(): Image? = this?.let { Image(it) }
