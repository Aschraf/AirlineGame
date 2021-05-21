import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.31"
  id("io.gitlab.arturbosch.detekt") version "1.17.1"
}

detekt {
  failFast = false
  buildUponDefaultConfig = true

  config = files("$projectDir/detekt/detekt-config.yml") // point to your custom config defining rules to run, overwriting default behavior
  baseline = file("$projectDir/detekt/detekt-baseline.xml") // a way of suppressing issues before introducing detekt

  input = files(
      subprojects.flatMap { project ->
        listOf(
            "${project.projectDir}/src/main/kotlin"
        )
      }
  )

  reports {
    html.enabled = true // observe findings in your browser with structure and code snippets
  }
}

allprojects {
  group = "com.airproject"

  repositories {
    mavenCentral()
    jcenter()
  }
}

val javaVersionString = "11"

subprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")

  dependencies {
    implementation(kotlin("stdlib-jdk8"))
  }

  tasks {

    withType<KotlinCompile>() {
      kotlinOptions {
        jvmTarget = javaVersionString
      }
    }

    withType<JavaCompile>().configureEach {
      sourceCompatibility = javaVersionString
      targetCompatibility = javaVersionString
    }
  }
}

