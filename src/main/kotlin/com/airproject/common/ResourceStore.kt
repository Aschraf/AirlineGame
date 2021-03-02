package com.airproject.common

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
    AIRLINE_FLAG("images/airline_flag.jpg"),
    BOEING_LOGO("images/manufacturer/boeing.svg"),
  }

  enum class Layout(override val path: String) : ILocalResource {
    MAIN_VIEW("/layout/MainView.fxml"),
    ACTION_PANEL("/layout/ActionPanel.fxml"),
    BUY_PLANE_LAYOUT("/layout/BuyPlaneView.fxml"),
  }

  enum class Map(override val path: String) : ILocalResource {
    AIRPORT("/common/airport.csv"),
    AIRPLANE("/common/planes.csv"),
    MANUFACTURER("/common/manufacturer.csv"),
  }

  const val stylesheet = "/style/AppStyle.css"
}

