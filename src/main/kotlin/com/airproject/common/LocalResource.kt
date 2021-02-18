package com.airproject.common

import java.net.URL

interface ILocalResource {
  val path: String

  fun url(): URL = this::class.java.getResource(path)
}

object LocalResource {
  enum class Image(override val path: String) : ILocalResource {
    EARTH_MAP("image/earth_map.jpg"),
    GRID("image/grid.png"),
    AIRLINE_FLAG("image/airline_flag.jpg"),
  }


  enum class Layout(override val path: String) : ILocalResource {
    MAIN_VIEW("/layout/MainView.fxml"),
    GAME_MENU("/layout/GameMenu.fxml"),
  }
}

