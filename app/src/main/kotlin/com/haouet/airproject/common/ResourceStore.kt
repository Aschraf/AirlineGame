package com.haouet.airproject.common

import java.io.InputStream
import java.net.URL

interface ILocalResource {
  val path: String

  fun url(): URL = this::class.java.getResource(path)
  fun getResourceStream(): InputStream? = javaClass.classLoader.getResourceAsStream(path)
}

object ResourceStore {
  enum class Image(override val path: String) : ILocalResource {
    EARTH_MAP("images/earth_map.jpg"),
    GRID("images/grid.png"),
  }

  enum class Layout(override val path: String) : ILocalResource {
    ACTION_PANEL("/layout/ActionPanel.fxml"),
    BUY_PLANE_LAYOUT("/layout/BuyPlaneView.fxml"),
    TOOL_BAR_PLANES("/layout/toolbar/ToolBarPlanes.fxml"),
  }

  enum class Icon(override val path: String) : ILocalResource {
    PLANE_BLACK("icons/plane.svg"),
    GEAR_BLACK("icons/gear.svg"),
  }

  const val stylesheet = "/style/AppStyle.css"
}

