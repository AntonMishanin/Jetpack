package com.example.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.Pokemon

class ListViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    val user: LiveData<Pokemon> = getPokemon()

    private fun getPokemon(): LiveData<Pokemon>{
        val pokemon = Pokemon()
        pokemon.name = "Test name"

        val data = MutableLiveData<Pokemon>()
        data.value = pokemon

        return data
    }
}