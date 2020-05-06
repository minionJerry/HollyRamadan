package com.kanykeinu.hollyramadan.data.fasting_dates.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kanykeinu.hollyramadan.data.common.Tables

@Entity(tableName = Tables.FASTING_DATES)
data class FastingDateEntity(
    @PrimaryKey
    @ColumnInfo(name = "number")
    var number: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "week_day")
    var weekDay: String,
    @ColumnInfo(name = "time_start")
    var timeStart: String,
    @ColumnInfo(name = "time_finish")
    var timeFinish: String
)