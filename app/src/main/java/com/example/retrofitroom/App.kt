package com.example.retrofitroom

import android.app.Application
import com.example.retrofitroom.presentation.di.*

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .localModule(LocalModule())
            .remoteModule(RemoteModule())
            .build()
    }
}