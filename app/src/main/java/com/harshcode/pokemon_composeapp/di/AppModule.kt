package com.harshcode.pokemon_composeapp.di

import com.harshcode.pokemon_composeapp.data.remote.PokeAPI
import com.harshcode.pokemon_composeapp.repository.PokemonRepository
import com.harshcode.pokemon_composeapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesPokemonRepository(api: PokeAPI ) = PokemonRepository(api)

    @Singleton
    @Provides
    fun providesPokeAPI() : PokeAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeAPI::class.java)
    }
}