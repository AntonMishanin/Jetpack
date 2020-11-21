package com.example.myapplication.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
data class PokemonDbEntity(
    @ColumnInfo(name = "name")
    var name: String = "",
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    //@Embedded
    //var pokemonResult: List<PokemonResultNetwork> = ArrayList()
)