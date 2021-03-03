package com.haouet.airproject.view.dynamicimage

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.layout.Pane
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

/**
 * Initial idea from: https://gist.github.com/james-d/ce5ec1fd44ce6c64e81a
 */
class DynamicImageView(image: Image) {

  val imageView = ImageView(image)

  val viewPortProperty: ObjectProperty<Rectangle2D> = imageView.viewportProperty()

  init {

    val width = image.width
    val height = image.height

    imageView.isPreserveRatio = false
    reset(width / 2, height / 2)

    val mouseDown: ObjectProperty<Point2D> = SimpleObjectProperty()

    imageView.setOnMousePressed { e ->
      if (e.button == MouseButton.SECONDARY) {
        val mousePress = adjust(Point2D(e.x, e.y))
        mouseDown.set(mousePress)
      }
    }

    imageView.setOnMouseDragged { e ->
      // Only drag on right click
      if (e.button == MouseButton.SECONDARY) {
        val dragPoint = adjust(Point2D(e.x, e.y))
        shift(imageView, dragPoint.subtract(mouseDown.get()))
        mouseDown.set(adjust(Point2D(e.x, e.y)))
      }
    }

    imageView.setOnScroll { e ->
      val delta = e.deltaY
      val viewport = viewPortProperty.get()
      val scale = clamp(
          value = 1.005.pow(-delta),
          // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction
          min = min(MIN_PIXELS / viewport.width, MIN_PIXELS / viewport.height),
          // don't scale so that we're bigger than image dimensions
          max = max(width / viewport.width, height / viewport.height)
      )
      val mouse = adjust(Point2D(e.x, e.y))
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

      viewPortProperty.set(Rectangle2D(newMinX, newMinY, newWidth, newHeight))
    }
  }


  /** Convert mouse coordinates in the imageView to coordinates in the actual image */
  fun adjust(imageViewCoordinates: Point2D): Point2D {
    val xProportion = imageViewCoordinates.x / imageView.boundsInLocal.width
    val yProportion = imageViewCoordinates.y / imageView.boundsInLocal.height

    val viewport = viewPortProperty.get()

    return Point2D(
        viewport.minX + xProportion * viewport.width,
        viewport.minY + yProportion * viewport.height
    )
  }

  /** Convert mouse coordinates in the imageView to coordinates in the actual image */
  fun raw(imageViewCoordinates: Point2D): Point2D {
    val viewport = viewPortProperty.get()

    val x = (imageViewCoordinates.x - viewport.minX) * (imageView.boundsInLocal.width / viewport.width)
    val y = (imageViewCoordinates.y - viewport.minY) * (imageView.boundsInLocal.height / viewport.height)

    return Point2D(x, y)
  }

  /** reset to the top left */
  private fun reset(width: Double, height: Double) {
    viewPortProperty.set(Rectangle2D(0.0, 0.0, width, height))
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
    val viewport = viewPortProperty.get()
    val width = imageView.image.width
    val height = imageView.image.height
    val maxX = width - viewport.width
    val maxY = height - viewport.height
    val minX = clamp(viewport.minX - delta.x, 0.0, maxX)
    val minY = clamp(viewport.minY - delta.y, 0.0, maxY)

    viewPortProperty.set(Rectangle2D(minX, minY, viewport.width, viewport.height))
  }

  fun fitTo(mainPane: Pane) {
    imageView.fitWidthProperty().bind(mainPane.widthProperty())
    imageView.fitHeightProperty().bind(mainPane.heightProperty())
  }

  companion object {
    private const val MIN_PIXELS = 10
  }
}