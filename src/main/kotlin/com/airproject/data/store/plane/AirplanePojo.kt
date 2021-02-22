package com.airproject.data.store.plane

data class AirplanePojo(
    val manufacturer: String,
    val modelName: String,
    val launchYear: Int,
    val maxSeat: Int,
    val speed: Int,
    val range: Int
)
