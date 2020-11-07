package com.example.myapplication.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Repository
import com.example.myapplication.data.database.PokemonDatabase
import com.example.myapplication.data.network.PokemonNetwork
import com.example.myapplication.data.network.RetrofitInstance
import com.example.myapplication.domain.mapper.PokemonMapper

class PokemonListFragment : Fragment() {

    private val adapter = PokemonListAdapter()

    private val r = RetrofitInstance()
    private val retrofitInstance = RetrofitInstance().provideRetrofit()
    private val api = r.providePokemonApi(retrofitInstance)
    private var network = PokemonNetwork(api)

    private var mapper = PokemonMapper()

    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonDatabase =
            PokemonDatabase.getPokemonDatabase(requireContext().applicationContext)
        val pokemonDao = pokemonDatabase.getPokemonDao()

        val repository = Repository(network, pokemonDao, mapper)
        viewModel.setRepository(repository)

        initView()

        viewModel.user?.observe(viewLifecycleOwner) {
           //adapter.setListCurrency(it.pokemonResult)
        }
    }

    fun initView() {
        //RecyclerView
        val recyclerViewPokemon =
            view?.findViewById<RecyclerView>(R.id.recyclerView_pokemon_list)
        recyclerViewPokemon?.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPokemon?.adapter = adapter

        adapter.setListener(object : PokemonItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.onItemPokemonClick()
            }
        })
    }

    fun navigateToPokemonDetail(position: Int) {

    }
}