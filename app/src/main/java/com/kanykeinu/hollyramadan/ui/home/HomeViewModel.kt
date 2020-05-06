package com.kanykeinu.hollyramadan.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.FastingDatesCacheSource
import com.kanykeinu.hollyramadan.data.util.DateFormats
import com.kanykeinu.hollyramadan.data.util.asString
import com.kanykeinu.hollyramadan.data.util.today
import com.kanykeinu.hollyramadan.data.util.weekDay
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class HomeViewModel : ViewModel() {
    private val datesDataSource = FastingDatesCacheSource

    private val _currentDate = MutableLiveData<String>().apply {
        value = today().asString(DateFormats.DAY_MONTH_YEAR)
    }
    val currentDate: LiveData<String> = _currentDate

    private val _currentDay = MutableLiveData<String>().apply {
        value = "Today, ${today().weekDay()}"
    }
    val currentDay = _currentDay

    @SuppressLint("CheckResult")
    fun getTodayFastingDates(): LiveData<FastingDate> {
        val dates = MutableLiveData<FastingDate>()
        datesDataSource.getTodayDates(today())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                dates.value = it
            }, onError = { Timber.d(it)
            })
        return dates
    }

    @SuppressLint("CheckResult")
    fun getTodayNotifications(): LiveData<FastingDate> {
        val dates = MutableLiveData<FastingDate>()
        datesDataSource.getTodayDates(today())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (onSuccess = {
                dates.value = it
            }, onError = { Timber.d(it)
            })
        return dates
    }

    @SuppressLint("CheckResult")
    fun getAllFastingDates(): LiveData<List<FastingDate>>{
        val dates = MutableLiveData<List<FastingDate>>()
        datesDataSource.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (onSuccess = {
                dates.value = it
            }, onError = { Timber.d(it)
            })
        return dates
    }
}