package com.example.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Repository
import com.example.myapplication.domain.entity.PokemonDbEntity

class ListViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var repository: Repository? = null

    var  user: LiveData<PokemonDbEntity>? =null

    fun setRepository(repository: Repository) {
        this.repository = repository
        user=  repository.requestPokemonList()
    }
}