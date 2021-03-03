package com.haouet.airproject.view.toolbar

import com.haouet.airproject.binding.getService
import com.haouet.airproject.common.ResourceStore
import com.haouet.airproject.notification.INotificationService
import com.haouet.airproject.util.image
import com.haouet.airproject.util.setOnPrimaryMouseClicked
import com.haouet.airproject.view.events.ToolBoxPressedEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.Tooltip
import javafx.scene.image.ImageView


class ToolBarPlanePm(
    private val notificationService: INotificationService = getService()
) {
  @FXML private lateinit var mainBox: Node

  @FXML private lateinit var planesImageView: ImageView
  @FXML private lateinit var planesLabel: Label

  @FXML private lateinit var maintenanceView: ImageView
  @FXML private lateinit var maintenanceLabel: Label


  @FXML
  fun initialize() {
    planesLabel.text = "0 / 0"
    maintenanceLabel.text = "100 %"

    planesImageView.image = ResourceStore.Icon.PLANE_BLACK.getResourceStream().image()
    maintenanceView.image = ResourceStore.Icon.GEAR_BLACK.getResourceStream().image()

    val t = Tooltip("Some tooltip about planes")
    Tooltip.install(mainBox, t)

    mainBox.setOnPrimaryMouseClicked {
      notificationService.notifyEvent(ToolBoxPressedEvent.AirplaneToolBox)
    }

  }

}