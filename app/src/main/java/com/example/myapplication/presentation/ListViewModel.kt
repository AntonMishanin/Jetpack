package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Repository
import com.example.myapplication.domain.entity.Pokemon
import com.example.myapplication.domain.entity.PokemonNetworkEntity

class ListViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val repository = Repository()

    val user: LiveData<PokemonNetworkEntity> = repository.requestPokemonList()

    private fun getPokemon(): LiveData<Pokemon>{
        val pokemon = Pokemon()
        pokemon.name = "Test name"

        val data = MutableLiveData<Pokemon>()
        data.value = pokemon

        val liveDate = repository.requestPokemonList()
        Log.d("TAG", "liveDate.count = ${liveDate.value?.next}")

        return data
    }
}