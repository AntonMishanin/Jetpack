package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.database.PokemonDao
import com.example.myapplication.data.network.PokemonNetwork
import com.example.myapplication.domain.callback.NetworkCallback
import com.example.myapplication.domain.entity.PokemonDbEntity
import com.example.myapplication.domain.entity.PokemonNetworkEntity
import com.example.myapplication.domain.mapper.PokemonMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class Repository(
    private val network: PokemonNetwork,
    private val pokemonDao: PokemonDao,
    private val mapper: PokemonMapper
) {


    fun requestPokemonList(): LiveData<PokemonDbEntity> {
        network.requestPokemon(object : NetworkCallback {
            override fun onSuccess(pokemon: PokemonNetworkEntity) {
                val pokemonDb = mapper.getDbPokemonFromNetwork(pokemon)

                CoroutineScope(IO).launch {
                    pokemonDao.save(pokemonDb)
                }
            }

            override fun onError(message: String) {

            }
        })
        return  pokemonDao.load(0)
    }
}