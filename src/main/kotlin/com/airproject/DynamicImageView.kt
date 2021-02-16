package com.airproject

import javafx.beans.InvalidationListener
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Dimension2D
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.ScrollEvent


class DynamicImageView(image: Image) {
  private val initialImageSize = Dimension2D(1000.0, 800.0)
  private val zoomProperty: DoubleProperty = SimpleDoubleProperty(1.0)
  private val imageView: ImageView = ImageView()
  private val scrollPane: ScrollPane = ScrollPane()
  private val zoomStep = 1.1


  init {
    println("Image: ${image.height} ${image.width}")

    addScrollListener()
    addImageChangeListener()

    imageView.image = image
    resizeImage(initialImageSize)
    imageView.preserveRatioProperty().set(true)
    scrollPane.content = imageView
  }

  private fun addScrollListener() {
    scrollPane.addEventFilter(ScrollEvent.ANY) { event ->
      if (event.isControlDown) {
        if (event.deltaY > 0)
          zoomProperty.set(zoomProperty.get() * zoomStep)
        else if (event.deltaY < 0)
          zoomProperty.set(zoomProperty.get() / zoomStep)
      }
    }
  }

  private fun addImageChangeListener() {
    zoomProperty.addListener(InvalidationListener {
      println("zoomProperty  $zoomProperty")

      imageView.fitWidth = initialImageSize.width * zoomProperty.get()
      imageView.fitHeight = initialImageSize.height * zoomProperty.get()
    })
  }

  private fun resizeImage(dimensions: Dimension2D) {
    imageView.fitHeight = dimensions.height
    imageView.fitWidth = dimensions.width
  }

  val component = scrollPane

}