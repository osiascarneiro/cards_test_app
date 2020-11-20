package com.osias.cards

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class CardsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CardsApplication)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}