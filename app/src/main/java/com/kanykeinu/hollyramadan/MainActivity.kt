package com.kanykeinu.hollyramadan

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kanykeinu.hollyramadan.util.getJsonDataFromAsset
import com.kanykeinu.hollyramadan.data.fasting_dates.cache.FastingDatesCacheSource
import com.kanykeinu.hollyramadan.data.hadiths.HadithCacheSource
import com.kanykeinu.hollyramadan.domain.fasting_dates.model.FastingDate
import com.kanykeinu.hollyramadan.domain.hadith.model.Hadith
import com.kanykeinu.hollyramadan.util.showSnackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)

        if (!FastingDatesCacheSource.isDatesSaved() || !HadithCacheSource.isHadithSaved())
            parseFiles()
    }

    @SuppressLint("CheckResult")
    private fun parseFiles() {
        val jsonFileDates = getJsonDataFromAsset(applicationContext, "ramadan_bishkek.json")
        val jsonFileHadiths = getJsonDataFromAsset(applicationContext, "hadiths.json")

        val fastingDateType = object : TypeToken<List<FastingDate>>() {}.type
        val hadithType = object : TypeToken<List<Hadith>>() {}.type

        val dates: List<FastingDate> = Gson().fromJson(jsonFileDates, fastingDateType)
        val hadiths: List<Hadith> = Gson().fromJson(jsonFileHadiths, hadithType)
        saveToDb(dates, hadiths)
    }

    @SuppressLint("CheckResult")
    private fun saveToDb(dates: List<FastingDate>, hadiths: List<Hadith>) {
        HadithCacheSource.saveAll(hadiths)
            .concatWith(FastingDatesCacheSource.saveAll(dates))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { nav_view.showSnackbar(it.message) },
                onComplete = {
                    navView.setupWithNavController(navController) },
                onNext = {
                    Timber.d(" Id is %s",it)
                }
            )
    }
}
