package com.example.myapplication.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.entity.PokemonDbEntity

class PokemonListAdapter  :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private var listPokemon: List<PokemonDbEntity> = ArrayList()
    private lateinit var listener: PokemonItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPokemon[position], position)
    }

    override fun getItemCount(): Int = listPokemon.size

    fun setListCurrency(listPokemon: List<PokemonDbEntity>) {
        this.listPokemon = listPokemon
        notifyDataSetChanged()
    }

    fun setListener(listener: PokemonItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pokemon, parent, false)) {

        private var pokemonTitleView: TextView? = null

        init {
            pokemonTitleView = itemView.findViewById(R.id.textView_item_pokemon_title)
        }

        fun bind(pokemon: PokemonDbEntity, position: Int) {
            pokemonTitleView?.text = pokemon.name

            itemView.setOnClickListener {
                listener.onItemClick(position)
            }
        }
    }
}

interface PokemonItemClickListener {

    fun onItemClick(position: Int)
}