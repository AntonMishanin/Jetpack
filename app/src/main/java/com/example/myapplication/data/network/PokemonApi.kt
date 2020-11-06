package com.example.myapplication.data.network

import com.example.myapplication.domain.entity.PokemonNetworkEntity
import io.reactivex.Single
import retrofit2.http.GET

interface PokemonApi {

    @GET("api/v2/ability/?limit=20&offset=20")
    fun getListOfPokemon(): Single<PokemonNetworkEntity>
}