package com.example.pokedex_app.pokedex

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
                    _response.value = "Success: ${response.body()?.count} Pokemon retrieved"
                }

                override fun onFailure(call: Call<NetworkPokemon>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

            })
    }
}