package com.example.pokedexv4.api


import android.net.Uri
import com.google.gson.annotations.SerializedName

data class Result(
    val name: String = "", // bulbasaur
    val url: String = "" // https://pokeapi.co/api/v2/pokemon/1/
)