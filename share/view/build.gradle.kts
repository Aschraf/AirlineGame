repositories {
  jcenter()
}

plugins {
  id("org.openjfx.javafxplugin") version "0.0.10"
}

javafx {
  version = "15.0.1"
  modules("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics")
}

dependencies {
  // FX elements
  implementation("de.codecentric.centerdevice:javafxsvg:1.3.0")
}