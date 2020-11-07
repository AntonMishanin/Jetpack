package com.example.myapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.myapplication.R
import com.example.myapplication.data.Repository
import com.example.myapplication.data.database.PokemonDatabase
import com.example.myapplication.data.network.PokemonNetwork
import com.example.myapplication.data.network.RetrofitInstance
import com.example.myapplication.domain.mapper.PokemonMapper

class ListFragment : Fragment() {


  private val r = RetrofitInstance()
  private val retrofitInstance = RetrofitInstance().provideRetrofit()
  private val api = r.providePokemonApi(retrofitInstance)
  private var network = PokemonNetwork(api)

  private var mapper = PokemonMapper()

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonDatabase = PokemonDatabase.getPokemonDatabase(requireContext().applicationContext)
        val pokemonDao = pokemonDatabase.getPokemonDao()

        val repository = Repository(network, pokemonDao, mapper)
        viewModel.setRepository(repository)

        viewModel.user?.observe(viewLifecycleOwner) {
            val pokemonNameView = view.findViewById<TextView>(R.id.textView_pokemon_name)
            pokemonNameView.text = it?.name
        }
    }
}