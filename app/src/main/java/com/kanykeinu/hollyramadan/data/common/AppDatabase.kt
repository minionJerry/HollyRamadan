package com.kanykeinu.hollyramadan.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.dao.FastingDateDao
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.model.FastingDateEntity
import com.kanykeinu.hollyramadan.data.hadiths.cache.dao.HadithDao
import com.kanykeinu.hollyramadan.data.hadiths.cache.model.HadithEntity

@Database(
    entities = [
        FastingDateEntity::class,
        HadithEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    internal abstract fun getFastingDateDao(): FastingDateDao
    internal abstract fun getHadithDao() : HadithDao
}