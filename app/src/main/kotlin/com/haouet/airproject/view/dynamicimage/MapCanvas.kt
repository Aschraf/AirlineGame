package com.haouet.airproject.view.dynamicimage

import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.util.image
import javafx.geometry.Dimension2D
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.layout.Pane
import javafx.scene.shape.Rectangle


data class MapComponent<T:Node>(val node: T, val mapX: Int, val mapY: Int)

class MapCanvas : Pane() {
  private val image = ResourceStore.Image.EARTH_MAP.getResourceStream().image() ?: throw IllegalStateException("Unable to load map")
  private val imageComponent = DynamicImageView(image)
  private val mapComponents = mutableListOf<MapComponent<*>>()

  val imageSize = Dimension2D(image.width, image.height)

  init {
    imageComponent.viewPortProperty.addListener { _ ->
      updateComponents()
    }

    clipChildren()


    children.addAll(imageComponent.imageView)
  }


  fun addViewToPane(parent: Pane) {
    imageComponent.fitTo(parent)

    // When resizing the window, we need to update the components
    parent.widthProperty().addListener { _ -> updateComponents() }
    parent.heightProperty().addListener { _ -> updateComponents() }

    parent.children.add(this)
  }


  fun addComponent(component: MapComponent<*>) {
    mapComponents.add(component)
    children.add(component.node)
  }

  fun addComponents(components: Collection<MapComponent<*>>) {
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