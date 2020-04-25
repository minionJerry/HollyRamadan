package com.kanykeinu.hollyramadan.domain.model

import com.google.gson.annotations.SerializedName

data class FastingDate(
    @SerializedName("number")
    var number: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("weekday")
    var weekDay: String,
    @SerializedName("time_start")
    var timeStart: String,
    @SerializedName("time_finish")
    var timeFinish: String
) {
    override fun toString(): String {
        return "$weekDay $timeStart - $timeFinish"
    }

}