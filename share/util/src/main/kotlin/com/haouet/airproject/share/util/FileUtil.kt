package com.haouet.airproject.share.util

object FileUtil {
  fun <T> loadCsvFile(path: String, parts: Int, convert: (List<String>) -> T) = this::class.java.getResourceAsStream(path)
      .bufferedReader()
      .useLines { lines ->
        lines.drop(1)    // Header
            .map { it.split(',') }
            .map {
              if (it.size != parts) throw IllegalArgumentException("Incorrect file format, failed at $it, expecting $parts parts but was ${it.size}")
              convert(it)
            }.toList()
      }
}