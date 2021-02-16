package com.airproject.dynamicimage

import javafx.beans.InvalidationListener
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.ScrollEvent


class DynamicImageView(image: Image) {
  private val imageCoor = ImageCoor(1000.0, 800.0)
  private val zoomProperty: DoubleProperty = SimpleDoubleProperty(1.0)
  private val imageView: ImageView = ImageView()
  private val scrollPane: ScrollPane = ScrollPane()


  init {
    println("Image: ${image.height} ${image.width}")

    addScrollListener()
    addImageChangeListener()

    imageView.image = image
    resizeImage(imageCoor)
    imageView.preserveRatioProperty().set(true)
    scrollPane.content = imageView
  }

  private fun addScrollListener() {
    scrollPane.addEventFilter(ScrollEvent.ANY) { event ->
      if (event.isControlDown) {
        if (event.deltaY > 0)
          imageCoor.zoom(ZoomDirection.IN)
        else if (event.deltaY < 0)
          imageCoor.zoom(ZoomDirection.OUT)

        // TODO: Listener or property
        resizeImage(imageCoor)
      }
    }
  }

  private fun addImageChangeListener() {
    zoomProperty.addListener(InvalidationListener {
      println("zoomProperty  $zoomProperty")

      imageView.fitWidth = imageCoor.initialWidth * zoomProperty.get()
      imageView.fitHeight = imageCoor.initialHeight * zoomProperty.get()
    })
  }

  private fun resizeImage(dimensions: ImageCoor) {
    dimensions.applyOn(imageView)
  }

  val component = scrollPane

}