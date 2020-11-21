package com.example.myapplication.domain.mapper

import com.example.myapplication.domain.entity.PokemonDbEntity
import com.example.myapplication.domain.entity.PokemonNetworkEntity
import com.example.myapplication.domain.entity.PokemonUiEntity

class PokemonMapper {

    fun getDbPokemonFromNetwork(pokemonNetwork: PokemonNetworkEntity): PokemonDbEntity {
        val pokemonDb = PokemonDbEntity()
        pokemonDb.id = 1
        pokemonDb.name = pokemonNetwork.next
        //pokemonDb.pokemonResult = pokemonNetwork.results

        return pokemonDb
    }

    fun getUiPokemonFromDb(pokemonDb: PokemonDbEntity?): PokemonUiEntity {
        val pokemonUi = PokemonUiEntity()
        pokemonUi.name = pokemonDb?.name ?: "Test"

        return pokemonUi
    }
}