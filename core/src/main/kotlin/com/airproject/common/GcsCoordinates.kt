package com.airproject.common

/**
 * Geographic coordinate system
 */
data class GcsCoordinates(val latitude:Double, val longitude:Double) {

  fun asPlanar(width:Double, height:Double): Pair<Double, Double> {
    val locX = width * (longitude + MAX_LONGITUDE) / TOTAL_LONGITUDE
    val locY = height * (MAX_LATITUDE - latitude) / TOTAL_LATITUDE

    return Pair(locX, locY)
  }

  companion object{

    private const val TOTAL_LONGITUDE = 360
    private const val MAX_LONGITUDE = 180

    private const val TOTAL_LATITUDE = 180
    private const val MAX_LATITUDE = 90
  }

}