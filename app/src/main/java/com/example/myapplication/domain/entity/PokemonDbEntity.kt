package com.example.myapplication.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
data class PokemonDbEntity(
    @ColumnInfo(name = "name")
    var name: String = "",
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    var pokemonResult: List<PokemonResultNetwork> = ArrayList()
)