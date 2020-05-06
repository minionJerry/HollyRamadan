package com.kanykeinu.hollyramadan.data.fasting_dates.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanykeinu.hollyramadan.data.common.Tables
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.model.FastingDateEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FastingDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(dates: List<FastingDateEntity>): Single<List<Long>>

    @Query("SELECT * FROM ${Tables.FASTING_DATES}")
    fun getAll(): Single<List<FastingDateEntity>>

    @Query("SELECT * FROM ${Tables.FASTING_DATES} where date = :date ")
    fun getTodayFastingDates(date: String) : Single<FastingDateEntity>

    @Query("DELETE FROM ${Tables.FASTING_DATES}")
    fun clear(): Completable
}
