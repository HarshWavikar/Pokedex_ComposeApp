package com.harshcode.pokemon_composeapp.repository

import com.harshcode.pokemon_composeapp.data.remote.PokeAPI
import com.harshcode.pokemon_composeapp.data.remote.response.Pokemon
import com.harshcode.pokemon_composeapp.data.remote.response.PokemonList
import com.harshcode.pokemon_composeapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeAPI
) {

    suspend fun getPokemonList(limit: Int, offset:Int): Resource<PokemonList> {
        val response = try {
           api.getPokemonList(limit = limit, offset =  offset)
        }catch (e: Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonDetail(pokemonName :String): Resource<Pokemon>{
        val response = try {
            api.getPokemonDetail(pokemonName)
        }catch (e:Exception){
            return  Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}