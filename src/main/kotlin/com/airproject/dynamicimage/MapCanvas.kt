package com.airproject.dynamicimage

import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.input.MouseButton
import javafx.scene.layout.Pane
import javafx.scene.shape.Rectangle


data class MapComponent(val node: Node, val mapX: Int, val mapY: Int)

class MapCanvas(parent: Pane, image: Image) : Pane() {
  private val imageComponent = DynamicImageView(image)

  private val rectangle = MapComponent(Rectangle(50.0, 50.0), 200, 100)

  init {
    imageComponent.fitTo(parent)

    // When resizing the window, we need to update the components
    parent.widthProperty().addListener { _ -> updateComponents() }
    parent.heightProperty().addListener { _ -> updateComponents() }

    imageComponent.viewPortProperty.addListener { _ ->
      updateComponents()
    }

    clipChildren()

    rectangle.node.setOnMousePressed { e->
      if (e.button == MouseButton.PRIMARY) {
        println("Button pressed!")
      }
    }

    children.addAll(imageComponent.imageView, rectangle.node)
  }

  private fun clipChildren() {
    val outputClip = Rectangle()
    clip = outputClip
    layoutBoundsProperty().addListener { _, _, newValue ->
      outputClip.width = newValue.width
      outputClip.height = newValue.height
    }
  }


  private fun updateComponents() {
    val newCoordinates = imageComponent.raw(Point2D(rectangle.mapX.toDouble(), rectangle.mapY.toDouble()))

    rectangle.node.translateX = newCoordinates.x
    rectangle.node.translateY = newCoordinates.y
  }


}