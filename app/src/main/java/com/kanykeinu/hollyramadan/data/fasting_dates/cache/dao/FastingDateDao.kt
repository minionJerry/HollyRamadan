package com.kanykeinu.hollyramadan.data.fasting_dates.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanykeinu.hollyramadan.data.Tables
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.model.FastingDateEntity
import com.kanykeinu.hollyramadan.domain.model.FastingDate
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FastingDateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(dates: List<FastingDateEntity>): Completable

    @Query("SELECT * FROM ${Tables.FASTING_DATES}")
    fun getAll(): Single<List<FastingDateEntity>>

    @Query("DELETE FROM ${Tables.FASTING_DATES}")
    fun clear()

}
