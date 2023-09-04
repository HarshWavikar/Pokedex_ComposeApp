package com.harshcode.pokemon_composeapp.data.remote

import com.harshcode.pokemon_composeapp.data.remote.response.Pokemon
import com.harshcode.pokemon_composeapp.data.remote.response.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeAPI {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset : Int
    ): PokemonList


    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): Pokemon
}