package com.haouet.airproject.data

import java.io.File

enum class PackageResource(val csvLocation: String, val imagesFolder: String?) {
  AIRPORT("airport.csv", null),
  AIRPLANE("planes.csv", "planes_img"),
  MANUFACTURER("manufacturer.csv", "manufacturer_img"),
}

class PackageLoader(private val path: String) {
  fun csvFile(packageResource: PackageResource) = File(path).resolve(packageResource.csvLocation)
  fun imageFile(packageResource: PackageResource, image: String) = packageResource.imagesFolder?.let { folder ->
    File(path).resolve(folder).resolve(image)
  }

}