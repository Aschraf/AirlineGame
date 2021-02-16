package com.airproject.dynamicimage

import javafx.beans.InvalidationListener
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.ScrollPane
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.input.ScrollEvent


// TODO: Doesn't consider zoom correctly
class DynamicImageView(image: Image) {
  private val imageCoor = ImageCoor(1000.0, 800.0)
  private val zoomProperty: DoubleProperty = SimpleDoubleProperty(1.0)
  private val imageView: ImageView = ImageView()
  private val scrollPane: ScrollPane = ScrollPane()

  private var scale: Double
    get() = myScale.get()
    set(scale) {
      myScale.set(scale)
    }

  var myScale: DoubleProperty = SimpleDoubleProperty(1.0)

  init {
    // add scale transform
    imageView.scaleXProperty().bind(myScale)
    imageView.scaleYProperty().bind(myScale)

    println("Image: ${image.height} ${image.width}")

    addScrollListener()
    addImageChangeListener()

    imageView.image = image
    resizeImage(imageCoor)
    imageView.preserveRatioProperty().set(true)
    scrollPane.content = imageView

    imageView.setOnMouseClicked {
      if (it.button == MouseButton.SECONDARY) {
        println("Moved to (${it.x}, ${it.y})")
      }
    }
  }

  private fun addScrollListener() {
    scrollPane.addEventFilter(ScrollEvent.ANY) { event ->
      if (event.isControlDown) {

        val delta = 1.2

        var scaleTemp = scale
        val oldScale = scale

        if (event.deltaY < 0) scaleTemp /= delta else scaleTemp *= delta
        scaleTemp = clamp(scaleTemp, MIN_SCALE, MAX_SCALE)

        val f = (scaleTemp / oldScale) - 1
        val dx = event.sceneX - (imageView.boundsInParent.width / 2 + imageView.boundsInParent.minX)
        val dy = event.sceneY - (imageView.boundsInParent.height / 2 + imageView.boundsInParent.minY)
        scale = scaleTemp

        // note: pivot value must be untransformed, i. e. without scaling
        setPivot(f * dx, f * dy)
        event.consume()
      }
    }
  }

  private fun setPivot(x: Double, y: Double) {
    imageView.translateX = imageView.translateX - x
    imageView.translateY = imageView.translateY - y
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


  companion object {
    private const val MAX_SCALE = 10.0
    private const val MIN_SCALE = .1

    fun clamp(value: Double, min: Double, max: Double): Double {
      if (value.compareTo(min) < 0) return min
      return if (value.compareTo(max) > 0) max else value
    }
  }
}