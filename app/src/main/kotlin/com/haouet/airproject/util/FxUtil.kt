package com.haouet.airproject.util

import javafx.scene.Node
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent

fun Node.setOnPrimaryMouseClicked(handle: (MouseEvent) -> Unit) {
  setOnMouseClicked {
    if (it.button == MouseButton.PRIMARY) {
      handle(it)
    }
  }
}