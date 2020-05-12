package com.kanykeinu.hollyramadan.data.hadiths.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kanykeinu.hollyramadan.data.common.Tables

@Entity(tableName = Tables.HADITHS)
data class HadithEntity(
    @PrimaryKey
    @ColumnInfo(name = "number")
    var number: String,
    @ColumnInfo(name = "text")
    var text: String,
    @ColumnInfo(name = "source")
    var source: String
)
