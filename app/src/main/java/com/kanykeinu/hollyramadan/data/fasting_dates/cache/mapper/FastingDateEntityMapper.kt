package com.kanykeinu.hollyramadan.data.fasting_dates.cache.mapper

import com.kanykeinu.hollyramadan.data.fasting_dates.cache.model.FastingDateEntity
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate

object FastingDateEntityMapper {

    fun mapToEntity(source : FastingDate) : FastingDateEntity =
        with(source) {
            FastingDateEntity(
                number, date, weekDay, timeStart, timeFinish
            )
        }

    fun mapFromEntity(source : List<FastingDateEntity>) : List<FastingDate> =
        source.map { mapFromEntity(it) }

    fun mapFromEntity(source : FastingDateEntity) : FastingDate =
        with(source) {
            FastingDate(
                number, date, weekDay, timeStart, timeFinish
            )
        }
}