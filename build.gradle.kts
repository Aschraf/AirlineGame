repositories{
  jcenter()
}

plugins {
  application
  kotlin("jvm") version "1.4.10"
  kotlin("plugin.serialization") version "1.4.10"
  id("com.github.johnrengelman.shadow") version "6.1.0"
  id("org.openjfx.javafxplugin") version "0.0.9"
  id("io.gitlab.arturbosch.detekt") version "1.16.0-RC1"
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

  implementation("org.koin:koin-core:2.2.2")
  implementation("com.jfoenix:jfoenix:9.0.10")

}

detekt {
  failFast = false
  buildUponDefaultConfig = true

  config = files("$projectDir/detekt/detekt-config.yml") // point to your custom config defining rules to run, overwriting default behavior
  baseline = file("$projectDir/detekt/detekt-baseline.xml") // a way of suppressing issues before introducing detekt

  reports {
    html.enabled = true // observe findings in your browser with structure and code snippets
  }
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