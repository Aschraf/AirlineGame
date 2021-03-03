package com.haouet.airproject.data.store.plane

import javafx.scene.image.Image

/**
 * data
 * airbus price https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&ved=2ahUKEwi9x67e3IXvAhXD4uAKHaX6DesQFjAKegQIAhAD&url=https%3A%2F%2Fwww.airbus.com%2Fcontent%2Fdam%2Fcorporate-topics%2Fpublications%2Fbackgrounders%2FBackgrounder-Airbus-Commercial-Aircraf-price-list-EN.pdf&usg=AOvVaw2tOyPxoytDTnc2unVc9EIx
 * boeing price https://www.boeing.com/company/about-bca/
 *
 * fuel: https://www.wikiwand.com/en/Fuel_economy_in_aircraft
 */
data class AirplanePojo(
  val manufacturer: String,
  val modelName: String,
  val image: String,
  val price: Int,
  val launchYear: Int,
  val maxSeat: Int,
  val speed: Int,
  val range: Int,
  val consumption: Float,
) {
  fun loadImage(): Image? = javaClass.classLoader.getResourceAsStream("images/plane/$image")?.let { Image(it) }

  val fullName: String = "$manufacturer $modelName"
}