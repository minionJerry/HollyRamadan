package com.kanykeinu.hollyramadan.data.fasting_dates.cache

import com.kanykeinu.hollyramadan.HollyRamadanApplication
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.mapper.FastingDateEntityMapper
import com.kanykeinu.hollyramadan.data.util.DateFormats
import com.kanykeinu.hollyramadan.data.util.asString
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate
import io.reactivex.Completable
import io.reactivex.Single

private const val IS_DATES_SAVED = "is_dates_saved"

object FastingDatesCacheSource {
    private val fatingDatesDao = HollyRamadanApplication.database().getFastingDateDao()
    private val cache = HollyRamadanApplication.cache()

    fun getTodayDates(millis: Long): Single<FastingDate> =
        fatingDatesDao.getTodayFastingDates(millis.asString(DateFormats.DATE_MONTH)).map(
            FastingDateEntityMapper::mapFromEntity
        )

    fun getAll(): Single<List<FastingDate>> =
        fatingDatesDao.getAll().map { FastingDateEntityMapper.mapFromEntity(it) }

    fun saveAll(dates: List<FastingDate>): Single<List<Long>> =
        fatingDatesDao.saveAll(dates.map { FastingDateEntityMapper.mapToEntity(it) })
            .doOnSuccess {
                saveIdentificator(true)
            }
            .doOnError { saveIdentificator(false) }

    private fun saveIdentificator(isSaved: Boolean) =
        cache.edit().putBoolean(IS_DATES_SAVED, isSaved)

    fun isDatesSaved() = cache.getBoolean(IS_DATES_SAVED, false)
}