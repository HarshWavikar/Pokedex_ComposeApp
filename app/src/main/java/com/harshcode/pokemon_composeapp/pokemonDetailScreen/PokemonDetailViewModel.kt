package com.harshcode.pokemon_composeapp.pokemonDetailScreen

import androidx.lifecycle.ViewModel
import com.harshcode.pokemon_composeapp.data.remote.response.Pokemon
import com.harshcode.pokemon_composeapp.repository.PokemonRepository
import com.harshcode.pokemon_composeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun loadPokemonInfo(pokemonName: String) : Resource<Pokemon>{
        return repository.getPokemonDetail(pokemonName = pokemonName)

    }
}