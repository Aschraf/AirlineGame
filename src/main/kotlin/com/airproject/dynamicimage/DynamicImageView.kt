package com.airproject.dynamicimage

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * Initial idea from: https://gist.github.com/james-d/ce5ec1fd44ce6c64e81a
 */
class DynamicImageView(image: Image) {
  private val imageView = ImageView(image)

  val component: ImageView get() = imageView

  init {
    val width = image.width
    val height = image.height

    imageView.isPreserveRatio = false
    reset(imageView, width / 2, height / 2)

    val mouseDown: ObjectProperty<Point2D> = SimpleObjectProperty()



    imageView.setOnMousePressed { e ->
      if (e.button == MouseButton.PRIMARY) {
        println("Primary click on (${e.x}, ${e.y}")
      } else if (e.button == MouseButton.SECONDARY) {
        val mousePress = imageViewToImage(imageView, Point2D(e.x, e.y))
        mouseDown.set(mousePress)
      }
    }

    imageView.setOnMouseDragged { e ->
      // Only drag on right click
      if (e.button == MouseButton.SECONDARY) {
        val dragPoint = imageViewToImage(imageView, Point2D(e.x, e.y))
        shift(imageView, dragPoint.subtract(mouseDown.get()))
        mouseDown.set(imageViewToImage(imageView, Point2D(e.x, e.y)))
      }
    }

    imageView.setOnScroll { e ->
      val delta = e.deltaY
      val viewport = imageView.viewport
      val scale = clamp(
          value = 1.005.pow(-delta),
          // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction
          min = min(MIN_PIXELS / viewport.width, MIN_PIXELS / viewport.height),
          // don't scale so that we're bigger than image dimensions
          max = max(width / viewport.width, height / viewport.height)
      )
      val mouse = imageViewToImage(imageView, Point2D(e.x, e.y))
      val newWidth = viewport.width * scale
      val newHeight = viewport.height * scale

      // To keep the visual point under the mouse from moving, we need
      // (x - newViewportMinX) / (x - currentViewportMinX) = scale
      // where x is the mouse X coordinate in the image

      // solving this for newViewportMinX gives

      // newViewportMinX = x - (x - currentViewportMinX) * scale

      // we then clamp this value so the image never scrolls out
      // of the imageview:
      val newMinX = clamp(mouse.x - (mouse.x - viewport.minX) * scale, 0.0, width - newWidth)
      val newMinY = clamp(mouse.y - (mouse.y - viewport.minY) * scale, 0.0, height - newHeight)
      imageView.viewport = Rectangle2D(newMinX, newMinY, newWidth, newHeight)
    }
  }

  /** convert mouse coordinates in the imageView to coordinates in the actual image */
  private fun imageViewToImage(imageView: ImageView, imageViewCoordinates: Point2D): Point2D {
    val xProportion = imageViewCoordinates.x / imageView.boundsInLocal.width
    val yProportion = imageViewCoordinates.y / imageView.boundsInLocal.height
    val viewport = imageView.viewport
    return Point2D(
        viewport.minX + xProportion * viewport.width,
        viewport.minY + yProportion * viewport.height
    )
  }

  /** reset to the top left */
  private fun reset(imageView: ImageView, width: Double, height: Double) {
    imageView.viewport = Rectangle2D(0.0, 0.0, width, height)
  }


  private fun clamp(value: Double, min: Double, max: Double): Double {
    if (value < min) return min
    return if (value > max) max else value
  }

  /**
   * shift the viewport of the imageView by the specified delta, clamping so
   * the viewport does not move off the actual image
   */
  private fun shift(imageView: ImageView, delta: Point2D) {
    val viewport = imageView.viewport
    val width = imageView.image.width
    val height = imageView.image.height
    val maxX = width - viewport.width
    val maxY = height - viewport.height
    val minX = clamp(viewport.minX - delta.x, 0.0, maxX)
    val minY = clamp(viewport.minY - delta.y, 0.0, maxY)
    imageView.viewport = Rectangle2D(minX, minY, viewport.width, viewport.height)
  }

  companion object {
    private const val MIN_PIXELS = 10
  }
}