package com.example.pokedexv4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedexv4.api.PokeApi
import com.example.pokedexv4.api.PokemonJson
import com.example.pokedexv4.api.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "PokeDex"
class PokeApiFetch {


    private val pokeApi: PokeApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        pokeApi = retrofit.create(PokeApi::class.java)
    }

    fun fetchPokemons(): MutableLiveData<List<Result>> {
        val responseLiveData: MutableLiveData<List<Result>> = MutableLiveData()
        val pokemonRequest: Call<PokemonJson> = pokeApi.fetchPokemons()

        pokemonRequest.enqueue(object : Callback<PokemonJson> {

            override fun onFailure(call: Call<PokemonJson>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(
                call: Call<PokemonJson>,
                response: Response<PokemonJson>
            ) {
               val pokeapiResponse: PokemonJson? = response.body()
                var pokemonResponse: List<Result>? = pokeapiResponse?.results
//                var pokemonItems: List<Result> = pokemonResponse??: mutableListOf()

                if (pokemonResponse != null) {
                    pokemonResponse = pokemonResponse.filterNot {
                        it.url.isBlank()
                    }
                }
                responseLiveData.value = pokemonResponse
            }
        })

        return responseLiveData
    }

}