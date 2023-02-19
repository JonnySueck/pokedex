package com.example.pokedex_app.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface PokeApiService {

    @GET("pokemon")
    fun getPokemon():
            Call<String>
}

object PokemonApi {
    val retrofitService: PokeApiService by lazy { retrofit.create(PokeApiService::class.java) }
}