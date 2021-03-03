package com.haouet.airproject.data.store.airport

import com.haouet.airproject.common.GcsCoordinates


data class AirportPojo(
    val sign: String,
    val name: String,
    val gcs: GcsCoordinates,
)