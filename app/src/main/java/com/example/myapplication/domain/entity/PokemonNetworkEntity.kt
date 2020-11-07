package com.example.myapplication.domain.entity

data class PokemonNetworkEntity (
    var next: String = "",
    var results: List<PokemonResultNetwork> = ArrayList()
)