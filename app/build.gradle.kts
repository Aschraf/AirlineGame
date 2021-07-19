repositories{
  jcenter()
}

plugins{
  id("org.openjfx.javafxplugin") version "0.0.9"
}

javafx {
  version = "15.0.1"
  modules("javafx.base", "javafx.controls", "javafx.fxml",  "javafx.graphics")
}

dependencies {
  implementation(project(":share:util"))
  implementation(project(":share:view"))

  // Dependency
  implementation("org.koin:koin-core:2.2.2")

  // FX elements
  implementation("de.codecentric.centerdevice:javafxsvg:1.3.0")

  // Logging
  implementation("ch.qos.logback:logback-core:1.2.4")
  implementation("ch.qos.logback:logback-classic:1.2.3")
  implementation("org.slf4j:slf4j-api:1.7.30")

  implementation("com.jfoenix:jfoenix:9.0.10")
}