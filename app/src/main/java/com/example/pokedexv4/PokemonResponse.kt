package com.example.pokedexv4

import com.example.pokedexv4.api.Result
import com.google.gson.annotations.SerializedName

class PokemonResponse {
    @SerializedName("results")
    lateinit var pokemonItems: List<Result>
}