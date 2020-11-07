package com.example.myapplication

import android.app.Application

open class PokemonApp : Application() {

    private var instance: PokemonApp? = null

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    fun getAppInstance(): PokemonApp {
        return instance ?: this
    }

}