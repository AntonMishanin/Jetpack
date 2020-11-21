package com.example.myapplication.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.data.database.PokemonDao
import com.example.myapplication.data.network.PokemonNetwork
import com.example.myapplication.domain.callback.NetworkCallback
import com.example.myapplication.domain.entity.PokemonDbEntity
import com.example.myapplication.domain.entity.PokemonNetworkEntity
import com.example.myapplication.domain.mapper.PokemonMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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

                // CoroutineScope(IO).launch {
                //     pokemonDao.save(pokemonDb)
                // }

                //INSERT with Coroutine
                /*
                CoroutineScope(IO).launch {
                    try {
                        coroutineScope {
                            val task = async {
                               // methodThatThrowsException()
                                pokemonDao.save(pokemonDb)
                            }
                            //updateUI("Ok ${task.await()}")
                        }
                    } catch (e: Throwable) {
                       // showError("Erro! ${e.message}")
                    }
                }

                 */


                //INSERT with RX

                Completable.fromAction { pokemonDao.insertWithRx(pokemonDb) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DisposableCompletableObserver() {
                        override fun onComplete() {
                            Log.d("TAG", "insert onComplete()")
                        }

                        override fun onError(e: Throwable) {
                            Log.d("TAG", "insert onError ${e.message}")
                        }
                    })


            }

            override fun onError(message: String) {

            }
        })
        return pokemonDao.load(0)
    }

    @SuppressLint("CheckResult")
    fun getAll() {
        pokemonDao.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("TAG", "list size = ${it.size}")
            }
    }
}