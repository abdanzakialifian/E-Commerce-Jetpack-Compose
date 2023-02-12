package com.app.ecommere.utils

import java.text.NumberFormat
import java.util.*
import java.util.regex.Pattern

fun Double.formatRupiah(): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(this)
}

fun String.emailPattern() = Pattern.compile(
    "[a-zA-Z\\d+._%\\-]{1,256}" +
            "@" +
            "[a-zA-Z\\d][a-zA-Z\\d\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z\\d][a-zA-Z\\d\\-]{0,25}" +
            ")+"
).matcher(this).matches()