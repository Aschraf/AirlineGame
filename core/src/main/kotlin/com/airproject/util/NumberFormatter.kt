package com.airproject.util

import java.util.*

object NumberFormatter {
  fun format(value: Int) = String.format(Locale.FRANCE, "%,d", value )    // Random locale, should be improved later

}


