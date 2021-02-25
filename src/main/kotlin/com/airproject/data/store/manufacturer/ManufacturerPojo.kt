package com.airproject.data.store.manufacturer

import javafx.scene.image.Image

data class ManufacturerPojo(val id:String, val fullName:String, val logo:String) {
  fun loadLogo(): Image? = javaClass.classLoader.getResourceAsStream("images/manufacturer/$logo")?.let { Image(it) }
}

