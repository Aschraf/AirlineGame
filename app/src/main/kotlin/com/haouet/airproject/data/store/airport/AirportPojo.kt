package com.haouet.airproject.data.store.airport

import com.haouet.airproject.common.GcsCoordinates
import java.io.File
import javafx.scene.image.Image


data class AirportPojo(
    val sign: String,
    val name: String,
    val image: File?,
    val gcs: GcsCoordinates,
) {
  fun loadImage(): Image? = image?.let { Image(it.toURI().toString()) }
}