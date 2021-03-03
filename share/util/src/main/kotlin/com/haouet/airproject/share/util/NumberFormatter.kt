package com.haouet.airproject.share.util

import java.util.Locale

object NumberFormatter {
  fun format(value: Int) = String.format(Locale.FRANCE, "%,d", value )    // Random locale, should be improved later

}


