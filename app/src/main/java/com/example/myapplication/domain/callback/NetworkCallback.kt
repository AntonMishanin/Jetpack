package com.example.myapplication.domain.callback

import com.example.myapplication.domain.entity.PokemonNetworkEntity

interface NetworkCallback {

    fun onSuccess(pokemon: PokemonNetworkEntity)

    fun onError(message: String)
}