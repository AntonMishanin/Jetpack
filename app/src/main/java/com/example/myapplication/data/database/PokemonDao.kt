package com.example.myapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myapplication.domain.entity.PokemonDbEntity


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
}