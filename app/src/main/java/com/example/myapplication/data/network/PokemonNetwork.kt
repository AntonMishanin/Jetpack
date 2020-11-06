package com.example.myapplication.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.entity.PokemonNetworkEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PokemonNetwork(private val api: PokemonApi) {

    fun requestPokemon(): LiveData<PokemonNetworkEntity> {
        val data = MutableLiveData<PokemonNetworkEntity>()

        api.getListOfPokemon()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<PokemonNetworkEntity>() {
                override fun onSuccess(pokemonList: PokemonNetworkEntity) {
                    data.value = pokemonList
                    Log.d("TAG", "pokemonList = ${pokemonList.next}")
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "e = ${e.message}")
                }
            })
        return data
    }
}