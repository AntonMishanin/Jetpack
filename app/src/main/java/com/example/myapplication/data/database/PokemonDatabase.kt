package com.example.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.domain.entity.PokemonDbEntity

@Database(entities = [PokemonDbEntity::class], version = 5, exportSchema = false)
abstract class PokemonDatabase :RoomDatabase(){
    abstract fun getPokemonDao(): PokemonDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getPokemonDatabase(context: Context) : PokemonDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, PokemonDatabase::class.java, "pokemon.db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}