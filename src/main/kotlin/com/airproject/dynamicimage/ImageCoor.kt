package com.airproject.dynamicimage

import javafx.scene.image.ImageView

enum class ZoomDirection {
  IN {
    override fun multiplier(step: Double) = step
  },
  OUT {
    override fun multiplier(step: Double) = 1 / step
  };

  abstract fun multiplier(step: Double): Double
}

data class ImageCoor(
    val initialHeight: Double,
    val initialWidth: Double,
    val initialTranslateX: Double = 0.0,
    val initialTranslateY: Double = 0.0,
) {
  private val zoomStep = 0.1

  private var height = initialHeight
  private var width = initialWidth
  private var translateX = initialTranslateX
  private var translateY = initialTranslateY


  fun zoom(direction: ZoomDirection, pointerX: Double, pointerY: Double, totalHeight: Double, totalWidth: Double) {
    println("Zoomed ${direction.name} at ($pointerX, $pointerY)")
    val zoomMultiplier = direction.multiplier(1 + zoomStep)

    val extraHeight = height * zoomMultiplier
    val extraWidth = width * zoomMultiplier

    height += extraHeight
    width *= extraWidth


    val percentx = (pointerX / width) * extraWidth

    val d = -percentx
    println("Translating by $d")

    translateX += d
//    translateY += pointerY * zoomMultiplier
  }

  fun applyOn(imageView: ImageView) {
    println("Resizing to ($height, $width) at ($translateX, $translateY)")
    imageView.translateX = translateX
//    imageView.translateY = translateY
    imageView.fitWidth = width
    imageView.fitHeight = height
  }

  companion object{
    private const val MAX_SCALE = 10.0
    private const val MIN_SCALE = .1

    fun clamp(value: Double, min: Double, max: Double): Double {
      if (value.compareTo(min) < 0) return min
      return if (value.compareTo(max) > 0) max else value
    }
  }

}