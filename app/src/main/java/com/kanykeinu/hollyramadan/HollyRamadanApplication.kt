package com.kanykeinu.hollyramadan

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.kanykeinu.hollyramadan.data.common.AppDatabase
import com.kanykeinu.hollyramadan.util.FlipperHelper
import timber.log.Timber

class HollyRamadanApplication : Application() {

    companion object {
        private const val DB_NAME = "holly_ramadan_db"
        const val LOCAL_STORE = "holly_ramadan_store"

        lateinit var application: HollyRamadanApplication
            private set

        fun database() : AppDatabase =
            application.database

        fun cache(): SharedPreferences =
            application.cache

    }

    private lateinit var database: AppDatabase
    private lateinit var cache: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        application = this
        database = provideDataBase(applicationContext)
        cache = provideSharedPreferences(applicationContext)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        FlipperHelper.initFlipper(this.applicationContext)
    }

    private fun provideDataBase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()

    private fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(LOCAL_STORE, Context.MODE_PRIVATE)
}