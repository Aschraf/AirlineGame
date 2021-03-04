package com.haouet.airproject.view

import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.SystemWideEvent
import javafx.animation.TranslateTransition
import javafx.scene.Node
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.util.Duration


interface ILeftPopViewHandler {
  fun showNode(node: Node)
  fun removeNode(node: Node)
}


class LeftPopViewHandler(
    private val parent: Pane,
    notificationService: INotificationService,
) : ILeftPopViewHandler {
  private var currentNode: Node? = null

  init {
    notificationService.addTypeListener(SystemWideEvent.EscapePressed::class) {
      currentNode?.let { removeNode(it) }
    }
  }

  @Suppress("MagicNumber")
  override fun showNode(node: Node) {
    currentNode?.let { removeNode(it) }

    val translateTransition = TranslateTransition(Duration(100.0), node)
    translateTransition.fromX = -node.layoutBounds.width
    translateTransition.toX = 0.0
    translateTransition.play()

    AnchorPane.setTopAnchor(node, 200.0)
    AnchorPane.setLeftAnchor(node, 0.0)

    parent.children.add(node)
    currentNode = node
  }

  override fun removeNode(node: Node) {
    parent.children.remove(node)
    currentNode = null
  }
}