package com.example.pokedexv4.api

import retrofit2.Call
import retrofit2.http.GET

interface PokeApi {
//    @GET(
//        "/pokemon"
//    )
//    fun fetchPokeApi(): Call<PokemonResponse>

    @GET("pokemon"+
                "?limit=2000")
    fun fetchPokemons(): Call<PokemonJson>
}