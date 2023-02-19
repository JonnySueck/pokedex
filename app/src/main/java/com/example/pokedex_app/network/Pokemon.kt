package com.example.pokedex_app.network


data class NetworkPokemon (
    val count: Int,
    val next: String,
    val results: List<Pokemon>,
        )

class Pokemon (
    val name: String,
    val url: String,
)
