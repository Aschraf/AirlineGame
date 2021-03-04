package com.haouet.airproject.data.store.plane

import java.io.File
import javafx.scene.image.Image

data class ManufacturerPojo(val id: String, val fullName: String, val logo: File?) {
  fun loadLogo(): Image? = logo?.let { Image(it.toURI().toString()) }
}

