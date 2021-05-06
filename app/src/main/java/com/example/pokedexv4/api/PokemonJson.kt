package com.example.pokedexv4.api


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PokemonJson(
    val count: Int = 0, // 1118
    val next: String = "", // https://pokeapi.co/api/v2/pokemon?offset=20&limit=20
    val previous: Any? = null, // null
    val results: List<Result> = listOf()
)