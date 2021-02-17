package com.airproject.view.dynamicimage

import javafx.geometry.Dimension2D
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.scene.shape.Rectangle


data class MapComponent(val node: Node, val mapX: Int, val mapY: Int)

class MapCanvas(parent: Pane, image: Image) : Pane() {
  private val imageComponent = DynamicImageView(image)
  private val mapComponents = mutableListOf<MapComponent>()

  val imageSize = Dimension2D(image.width, image.height)

  init {
    imageComponent.fitTo(parent)

    // When resizing the window, we need to update the components
    parent.widthProperty().addListener { _ -> updateComponents() }
    parent.heightProperty().addListener { _ -> updateComponents() }

    imageComponent.viewPortProperty.addListener { _ ->
      updateComponents()
    }

    clipChildren()


    children.addAll(imageComponent.imageView)
  }

  fun addComponent(component: MapComponent) {
    mapComponents.add(component)
    children.add(component.node)
  }

  fun addComponents(components: Collection<MapComponent>) {
    components.forEach { addComponent(it) }
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
    mapComponents.forEach { component ->
      val newCoordinates = imageComponent.raw(Point2D(component.mapX.toDouble(), component.mapY.toDouble()))

      component.node.translateX = newCoordinates.x
      component.node.translateY = newCoordinates.y
    }

  }


}