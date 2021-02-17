package com.airproject.data.store.airport

import com.airproject.common.GcsCoordinates


data class AirportPojo(
    val sign: String,
    val name: String,
    val gcs: GcsCoordinates,
)