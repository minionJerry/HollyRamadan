package com.kanykeinu.hollyramadan.data.util

import android.content.Context
import com.kanykeinu.hollyramadan.R
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

fun timestamp(): Long =
    System.currentTimeMillis()

fun Date.asStringOrEmpty(format: String): String =
    if (time == Date(0).time) "" else SimpleDateFormat(format).format(this)

fun Date.asString(format: String): String =
    SimpleDateFormat(format).format(this)

fun Long.asString(format: String): String =
    SimpleDateFormat(format, Locale("ru")).format(Date(this))

fun Date.isSameDay(date: Date): Boolean {
    val cal1: Calendar = Calendar.getInstance()
    cal1.time = this
    val cal2: Calendar = Calendar.getInstance()
    cal2.time = date
    return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

fun Date.isYesterday(date: Date): Boolean {
    val cal1: Calendar = Calendar.getInstance()
    cal1.time = this
    val cal2: Calendar = Calendar.getInstance()
    cal2.time = date
    cal2.add(Calendar.DAY_OF_YEAR, -1)
    return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

fun today(): Long =
    Calendar.getInstance()
        .timeInMillis

fun tomorrow(): Long =
    Calendar.getInstance()
        .timeInMillis
        .plusDay(1)

fun Long.daysInMonth(): Int =
    Calendar.getInstance().apply {
        timeInMillis = this@daysInMonth
    }.getActualMaximum(Calendar.DAY_OF_MONTH)

val Long.hour: Int
    get() = Calendar.getInstance().apply {
        timeInMillis = this@hour
    }.get(Calendar.HOUR)

val Long.day: Int
    get() = Calendar.getInstance().apply {
        timeInMillis = this@day
    }.get(Calendar.DAY_OF_MONTH)

val Long.month: Int
    get() = Calendar.getInstance().apply {
        timeInMillis = this@month
    }.get(Calendar.MONTH)

fun Long.plusHour(hour: Int = 1): Long =
    Calendar.getInstance().apply {
        timeInMillis = this@plusHour
        add(Calendar.HOUR_OF_DAY, hour)
    }.timeInMillis

fun Long.minusHour(hour: Int = -1): Long =
    Calendar.getInstance().apply {
        timeInMillis = this@minusHour
        add(Calendar.HOUR_OF_DAY, hour)
    }.timeInMillis

fun Long.plusDay(day: Int = 1): Long =
    Calendar.getInstance().apply {
        timeInMillis = this@plusDay
        add(Calendar.DAY_OF_MONTH, day)
    }.timeInMillis

fun Long.minusDay(day: Int = -1): Long =
    Calendar.getInstance().apply {
        timeInMillis = this@minusDay
        add(Calendar.DAY_OF_MONTH, day)
    }.timeInMillis

fun Long.plusMonth(month: Int = 1): Long =
    Calendar.getInstance().apply {
        timeInMillis = this@plusMonth
        add(Calendar.MONTH, month)
    }.timeInMillis

fun Long.minusMonth(month: Int = -1): Long =
    Calendar.getInstance().apply {
        timeInMillis = this@minusMonth
        add(Calendar.MONTH, month)
    }.timeInMillis

fun Long.plusYear(year: Int = 1): Long =
    Calendar.getInstance().apply {
        timeInMillis = this@plusYear
        add(Calendar.YEAR, year)
    }.timeInMillis

fun Long.minusYear(year: Int = -1): Long =
    Calendar.getInstance().apply {
        timeInMillis = this@minusYear
        add(Calendar.YEAR, year)
    }.timeInMillis

fun String.asDate(format: String, locale: Locale = Locale.ENGLISH): Date? =
    try {
        SimpleDateFormat(format, locale).parse(this)
    } catch (ex: Exception) {
        null
    }

fun String.dateFormat(
    fromString: String,
    toString: String,
    locale: Locale = Locale.ENGLISH
): String =
    asDate(fromString, locale)?.asString(toString).orEmpty()

fun TimeZone.convertTimeZoneToString(): String {
    val offsetInMillis = this.rawOffset
    val sign = if (offsetInMillis >= 0) "+" else "-"
    val offset = String.format("%02d:%02d", abs(offsetInMillis / 3600000), abs((offsetInMillis / 60000) % 60))
    return "${this.displayName} $sign$offset"
}

fun Long.weekDay(): String =
    this.asString(DateFormats.WEEK_DAY)

object DateFormats {

    const val DAY_MONTH_YEAR = "dd MMMM yyyy"
    const val DATE_MONTH = "d MMMM"
    const val WEEK_DAY = "EEEE"
    const val DAY_MONTH_YEAR_WITH_POINTS = "dd.MM.yyyy"
    const val DAY_MONTH_YEAR_SHORT = "dd.MM.yy"
    const val HOURS_MINUTES = "HH:mm"
    const val ACTIVITY_SERVER_DATE = "HH:mm dd.MM.yyyy"
    const val ISO_8601 = "yyyy-MM-dd hh:mm:ss.SSS"
    const val ISO_8601_TIMEZONE = "yyyy-MM-dd hh:mm:ss XXX"
    const val SERVER_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy"
    const val DEADLINE = "dd.MM.yyyy HH:mm"
}
