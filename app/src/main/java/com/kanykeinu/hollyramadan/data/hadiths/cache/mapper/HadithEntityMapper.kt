package com.kanykeinu.hollyramadan.data.hadiths.cache.mapper

import com.kanykeinu.hollyramadan.data.hadiths.cache.model.HadithEntity
import com.kanykeinu.hollyramadan.domain.hadith.model.Hadith

object HadithEntityMapper {
    fun mapFromEntity(entity: HadithEntity): Hadith =
        with(entity) {
            Hadith(
                number = number,
                text = text,
                source = source
            )
        }

    fun mapToEntity(hadith : Hadith) : HadithEntity =
        with(hadith) {
            HadithEntity(
                number, text, source
            )
        }
}