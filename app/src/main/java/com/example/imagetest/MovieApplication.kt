package com.example.imagetest

import android.app.Application
import android.content.Context

class MovieApplication: Application() {

    /**
     * Initialize firebase, logger, analytics
     * dependency injection.
     *
     */
    override fun onCreate() {
        super.onCreate()
        movieApplication= applicationContext
    }
    companion object {
        lateinit var movieApplication: Context
    }


}