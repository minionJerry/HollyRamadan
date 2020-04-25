package com.kanykeinu.hollyramadan

import android.annotation.SuppressLint
import android.app.Person
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanykeinu.hollyramadan.base.getJsonDataFromAsset
import com.kanykeinu.hollyramadan.data.common.AppDatabase
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.mapper.FastingDateEntityMapper
import com.kanykeinu.hollyramadan.domain.model.FastingDate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    companion object {
        const val IS_DATA_SAVED = "is_data_saved"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        if (!HollyRamadanApplication.cache().getBoolean(IS_DATA_SAVED, false))
            parseFiles()
    }

    @SuppressLint("CheckResult")
    private fun parseFiles() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "ramadan_bishkek.json")
        Log.i("data", jsonFileString)

        val fastingDateList = object : TypeToken<List<FastingDate>>() {}.type

        var dates: List<FastingDate> = Gson().fromJson(jsonFileString, fastingDateList)
        HollyRamadanApplication.database().getFastingDateDao()
            .save(dates.map { FastingDateEntityMapper.mapToEntity(it) })
            .doOnError { i ->
                HollyRamadanApplication.cache().edit().putBoolean(IS_DATA_SAVED, false).apply()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(  AndroidSchedulers.mainThread())
            .subscribe {
                HollyRamadanApplication.cache().edit().putBoolean(IS_DATA_SAVED, true).apply()
            }
    }
}
