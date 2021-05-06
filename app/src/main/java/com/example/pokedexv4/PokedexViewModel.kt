package com.example.pokedexv4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedexv4.api.Result

class PokedexViewModel:ViewModel() {
    val pokedexLiveData: LiveData<List<Result>> = PokeApiFetch().fetchPokemons()
}