package com.kanykeinu.hollyramadan.data.hadiths.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanykeinu.hollyramadan.data.common.Tables
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.model.FastingDateEntity
import com.kanykeinu.hollyramadan.data.hadiths.cache.model.HadithEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HadithDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(hadiths: List<HadithEntity>): Single<List<Long>>

    @Query("SELECT * FROM ${Tables.HADITHS}")
    fun getAll(): Single<List<HadithEntity>>

    @Query("DELETE FROM ${Tables.HADITHS}")
    fun clear(): Completable
}