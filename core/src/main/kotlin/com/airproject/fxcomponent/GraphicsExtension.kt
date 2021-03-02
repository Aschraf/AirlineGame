package com.airproject.fxcomponent

import javafx.scene.Node

/** Set background to a hex color (eg: #123456, #FA0012). */
fun Node.setBackgroundColor(color: String) {
  style += "-fx-background-color: $color;"
}

/** Set text fill color to a hex color (eg: #123456, #FA0012).  */
fun Node.setTextColor(color: String) {
  style += "-fx-text-fill: $color;"
}
