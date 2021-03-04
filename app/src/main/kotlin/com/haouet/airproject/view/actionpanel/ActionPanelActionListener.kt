package com.haouet.airproject.view.actionpanel

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.notification.SystemWideEvent
import com.haouet.airproject.notification.ToolBoxPressedEvent
import com.haouet.airproject.util.loadRegion
import com.haouet.airproject.view.ILeftPopViewHandler
import javafx.scene.layout.Region

class ActionPanelActionListener(
    private val notificationService: INotificationService = getService(),
    private val leftPopViewHandler: ILeftPopViewHandler = getService(),
) {

  private val actionPanelView = lazyOf(createView())

  private fun createView(): Region {
    val controller = ActionPanelController { leftPopViewHandler.removeNode(it) }
    return ResourceStore.Layout.ACTION_PANEL.loadRegion(controller)
  }

  fun start() {
    notificationService.addTypeListener(SystemWideEvent.EscapePressed::class) {
      if (actionPanelView.isInitialized()) {
        leftPopViewHandler.removeNode(actionPanelView.value)
      }
    }

    notificationService.addTypeListener(ToolBoxPressedEvent::class) {
      leftPopViewHandler.showNode(actionPanelView.value)
    }
  }
}