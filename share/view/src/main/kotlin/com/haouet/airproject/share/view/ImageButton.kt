package com.haouet.airproject.share.view

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView


fun Button.imageButton(image:Image, fitWidth:Double, fitHeight:Double){
  val node = ImageView(image)
  node.fitHeight = fitHeight
  node.fitWidth = fitWidth
  graphic = node
  padding = Insets.EMPTY

}