package com.kanykeinu.hollyramadan.domain.hadith.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Hadith(
    @SerializedName("id")
    val number: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("source")
    val source: String
)