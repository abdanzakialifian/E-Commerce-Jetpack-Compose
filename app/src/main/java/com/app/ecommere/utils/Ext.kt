package com.app.ecommere.utils

import java.text.NumberFormat
import java.util.*

fun Double.formatRupiah(): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(this)
}