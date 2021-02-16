package com.airproject.dynamicimage

import javafx.scene.image.ImageView

enum class ZoomDirection {
  IN{
    override fun newDim(oldDim: Double, step:Double)  = oldDim * step
  },
  OUT{
    override fun newDim(oldDim: Double, step:Double)  = oldDim / step
  };
  abstract fun newDim(oldDim:Double, step:Double):Double
}

data class ImageCoor(
    val initialHeight: Double,
    val initialWidth: Double,
    val translateX: Double = 0.0,
    val translateY: Double = 0.0
) {
  private val zoomStep = 1.1

  private var height = initialHeight
  private var width = initialWidth

  fun zoom(direction: ZoomDirection) {
    height = direction.newDim(height, zoomStep)
    width = direction.newDim(width, zoomStep)
  }

  fun applyOn(imageView: ImageView) {
    println("Resizing to ($width, $height) ")
    imageView.translateX = translateX
    imageView.translateY = translateY
    imageView.fitWidth = width
    imageView.fitHeight = height
  }


}