package com.kanykeinu.hollyramadan.ui.dashboard

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.FastingDatesCacheSource
import com.kanykeinu.hollyramadan.data.hadiths.HadithCacheSource
import com.kanykeinu.hollyramadan.data.util.today
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate
import com.kanykeinu.hollyramadan.domain.hadith.model.Hadith
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DashboardViewModel : ViewModel() {
    private val hadithDataSource = HadithCacheSource

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    @SuppressLint("CheckResult")
    fun getHadiths(): LiveData<List<Hadith>> {
        val hadiths = MutableLiveData<List<Hadith>>()
        hadithDataSource.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                hadiths.value = it
            }, onError = { Timber.d(it)
            })
        return hadiths
    }
}