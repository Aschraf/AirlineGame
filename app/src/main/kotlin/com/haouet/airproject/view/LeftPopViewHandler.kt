package com.haouet.airproject.view

import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.SystemWideEvent
import javafx.animation.TranslateTransition
import javafx.scene.Node
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.util.Duration

enum class Position(val location: Double) {
  POS1(200.0),
  POS2(550.0),
}


interface ILeftPopViewHandler {
  fun showNode(node: Node, position: Position = Position.POS1)
  fun removeNode(node: Node, position: Position = Position.POS1)
}


class LeftPopViewHandler(
    private val parent: Pane,
    notificationService: INotificationService,
) : ILeftPopViewHandler {
  private var currentNode1: Node? = null
  private var currentNode2: Node? = null

  init {
    notificationService.addTypeListener(SystemWideEvent.EscapePressed::class) {
      currentNode1?.let { removeNode(it, Position.POS1) }
      currentNode2?.let { removeNode(it, Position.POS2) }
    }
  }

  @Suppress("MagicNumber")
  override fun showNode(node: Node, position: Position) {
    (if (position == Position.POS1) currentNode1 else currentNode2)?.let { removeNode(it, position) }

    val translateTransition = TranslateTransition(Duration(100.0), node)
    translateTransition.fromX = -node.layoutBounds.width
    translateTransition.toX = 0.0
    translateTransition.play()

    AnchorPane.setTopAnchor(node, position.location)
    AnchorPane.setLeftAnchor(node, 0.0)

    parent.children.add(node)

    if (position == Position.POS1) currentNode1 = node else currentNode2 = node
  }

  override fun removeNode(node: Node, position: Position) {
    parent.children.remove(node)
    if (position == Position.POS1) currentNode1 = null else currentNode2 = null
  }
}