
plugins {
  application
  kotlin("jvm") version "1.4.10"
  kotlin("plugin.serialization") version "1.4.10"
  id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "airline"
version = "0.1-SNAPSHOT"

val javaVersionString = "11"
val javaVersion = JavaVersion.toVersion(javaVersionString)

java {
  sourceCompatibility = javaVersion
  targetCompatibility = javaVersion
}

application {
  mainClassName = "com.optravis.api.MainClassKt"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))

  implementation("com.github.ajalt:clikt:2.8.0")
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