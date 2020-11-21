package com.example.myapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.*
import androidx.room.Query
import com.example.myapplication.domain.entity.PokemonDbEntity
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface PokemonDao {

    @Insert(onConflict = ABORT)
    fun save(pokemon: PokemonDbEntity)

    @Insert(onConflict = ABORT)
    fun insert(pokemonList: List<PokemonDbEntity>)

    @Query("SELECT * FROM Pokemon WHERE id = :pokemonId")
    fun load(pokemonId: Int): LiveData<PokemonDbEntity>

    @Query("SELECT * FROM Pokemon")
    fun loadAllPokemon(): LiveData<List<PokemonDbEntity>>

    /**
     * Rx
     */
    @Insert(onConflict = REPLACE)
    fun insertWithRx(pokemon: PokemonDbEntity)

    @Query("SELECT * FROM Pokemon")
    fun getAll(): Flowable<List<PokemonDbEntity>>

    @Query("SELECT * FROM Pokemon WHERE id = :id")
    fun getById(id: Int): Flowable<PokemonDbEntity>
}