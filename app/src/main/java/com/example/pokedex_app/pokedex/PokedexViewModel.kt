package com.example.pokedex_app.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex_app.network.NetworkPokemon
import com.example.pokedex_app.network.PokemonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokedexViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response

    init {
        getPokemonForPokedex()
    }

    private fun getPokemonForPokedex() {
        PokemonApi.retrofitService.getPokemon().enqueue(
            object: Callback<NetworkPokemon> {

                override fun onResponse(call: Call<NetworkPokemon>,
                                        response: Response<NetworkPokemon>) {
                    val pokemonList = response.body()?.results
                    var pokemonNames = ""
                    if (pokemonList != null) {
                        for (pokemon in pokemonList) {
                            pokemonNames += pokemon.name + ", "
                        }
                    }
                    _response.value = "${pokemonNames}"
                    Log.i("pokemon", "${response.body()?.results?.get(1)}")
                }

                override fun onFailure(call: Call<NetworkPokemon>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

            })
    }
}