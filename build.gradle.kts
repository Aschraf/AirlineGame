
plugins {
  application
  kotlin("jvm") version "1.4.10"
  kotlin("plugin.serialization") version "1.4.10"
  id("com.github.johnrengelman.shadow") version "6.1.0"
  id("org.openjfx.javafxplugin") version "0.0.9"
}

group = "airline"
version = "0.1-SNAPSHOT"

val javaVersionString = "11"
val javaVersion = JavaVersion.toVersion(javaVersionString)


java {
  sourceCompatibility = javaVersion
  targetCompatibility = javaVersion
}

javafx {
  version = "15.0.1"
  modules("javafx.base", "javafx.controls", "javafx.fxml",  "javafx.graphics")
}

application {
  mainClass.set("com.airproject.MainClassKt")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
}

tasks{
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
      jvmTarget = javaVersionString
    }
  }

  withType<JavaCompile>().configureEach {
    sourceCompatibility = javaVersionString
    targetCompatibility = javaVersionString
  }

  val generate by creating {
    dependsOn("generateProto")
  }

  assemble {
    dependsOn("shadowJar")
  }
}