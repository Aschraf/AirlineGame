package com.haouet.airproject.share.view

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority

/**
 * A simple text cell with an optional left and/or right image icon.
 */
@Suppress("MagicNumber")
class IconListCell(leftIcon: Image? = null, text: String, textColor: String? = null, rightIcon: Image? = null) : HBox() {

  private val leftIconView: ImageView? = leftIcon?.asImageView()
  private val rightIconView: ImageView? = rightIcon?.asImageView()

  val label: Label = Label().apply {
    maxWidth = Double.MAX_VALUE
    maxHeight = Double.MAX_VALUE

    setHgrow(this, Priority.ALWAYS)

    setText(text)

    if (textColor != null) setTextColor(textColor)
  }

  init {
    alignment = Pos.CENTER_LEFT

    maxHeight = Double.MAX_VALUE
    minHeight = Double.MIN_VALUE
    prefHeight = 24.0

    maxWidth = Double.MAX_VALUE
    minWidth = Double.MIN_VALUE

    spacing = 10.0

    children.addAll(listOfNotNull(leftIconView, label, rightIconView))
  }

  private fun Image.asImageView(): ImageView = ImageView().apply {
    fitHeight = 24.0
    fitWidth = 24.0
    isPickOnBounds = true
    isPreserveRatio = true

    image = this@asImageView
  }
}
