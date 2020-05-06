package com.kanykeinu.hollyramadan.domain.hadith.model

import com.google.gson.annotations.SerializedName

data class Hadith(
    @SerializedName("number")
    val number: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("source")
    val source: String
)