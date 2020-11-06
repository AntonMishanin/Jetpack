package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.network.PokemonNetwork
import com.example.myapplication.data.network.RetrofitInstance
import com.example.myapplication.domain.entity.PokemonNetworkEntity

class Repository {

    private var r = RetrofitInstance()
    private val retrofitInstance = RetrofitInstance().provideRetrofit()
    private val api = r.providePokemonApi(retrofitInstance)
    private val network = PokemonNetwork(api)

    fun requestPokemonList(): LiveData<PokemonNetworkEntity> {
       return network.requestPokemon()
    }
}