package com.kanykeinu.hollyramadan.data.hadiths

import com.kanykeinu.hollyramadan.HollyRamadanApplication
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.mapper.FastingDateEntityMapper
import com.kanykeinu.hollyramadan.data.hadiths.cache.mapper.HadithEntityMapper
import com.kanykeinu.hollyramadan.domain.hadith.model.Hadith
import io.reactivex.Completable
import io.reactivex.Single

private const val IS_HADITHS_SAVED = "is_hadiths_saved"

object HadithCacheSource {
    private val hadithDao = HollyRamadanApplication.database().getHadithDao()
    private val cache = HollyRamadanApplication.cache()

    fun saveAll(hadiths: List<Hadith>): Single<List<Long>> =
        hadithDao.saveAll(hadiths.map { HadithEntityMapper.mapToEntity(it) })
            .doOnError { saveIdentificator(false) }
            .doOnSuccess { saveIdentificator(true) }


    fun getAll() = hadithDao.getAll().map { HadithEntityMapper.mapFromEntity(it) }

    private fun saveIdentificator(isSaved: Boolean) =
        cache.edit().putBoolean(IS_HADITHS_SAVED, isSaved)

    fun isHadithSaved() = cache.getBoolean(IS_HADITHS_SAVED, false)
}