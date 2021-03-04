package com.haouet.airproject.share.util

import java.io.File

fun <T> File.loadCsvFile(parts: Int, convert: (List<String>) -> T) = this
    .bufferedReader()
    .useLines { lines ->
      lines.drop(1)    // Header
          .map { it.split(',') }
          .map {
            if (it.size != parts) throw IllegalArgumentException("Incorrect file format, failed at $it, expecting $parts parts but was ${it.size}")
            convert(it)
          }.toList()
    }