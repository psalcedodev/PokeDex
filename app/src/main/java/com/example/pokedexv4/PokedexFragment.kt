package com.example.pokedexv4

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexv4.api.Result
import com.squareup.picasso.Picasso
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
private lateinit var mediaPlayer: MediaPlayer
private const val TAG = "PhotoGalleryFragment"
class PokedexFragment : Fragment() {


    private lateinit var pokedexViewModel: PokedexViewModel
    private lateinit var pokemonRecyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokedexViewModel =
            ViewModelProviders.of(this).get(PokedexViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_pokedex, container, false)
        pokemonRecyclerView = view.findViewById(R.id.rv_pokedex)
        pokemonRecyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokedexViewModel.pokedexLiveData.observe(
            viewLifecycleOwner,
            Observer { pokemonItems ->
                pokemonRecyclerView.adapter = PokemonAdapter(pokemonItems)
            })
    }

    private inner class PokemonHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView),  View.OnClickListener{

        private lateinit var pokemonResult: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bindGalleryItem(item: Result) {
            pokemonResult = item
        }

        private val imageView: ImageView = itemView.findViewById(R.id.image_view)
        private val pokemonName: TextView = itemView.findViewById(R.id.pokemon_name)

        fun bindImageUrl(url:String){
//            Picasso.get().load(url).fit().into(imageView);
            view?.let { Glide.with(it)
                .load(url)
                .override(200, 200) // resize the image to these dimensions (in pixel)
                .optionalFitCenter() // this cropping technique scales the image so that it fills the requested bounds and then crops the extra.
                .into(imageView) };
        }

        @SuppressLint("SetTextI18n")
        fun bindPokemonName(name: String){ pokemonName.text = name[0].toUpperCase().toString()+name.substring(1) }
        override fun onClick(view: View) {

            val url = "https://pokemondb.net/pokedex/" + pokemonResult.name
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    private inner class  PokemonAdapter(private val galleryItems: List<Result>)
        : RecyclerView.Adapter<PokemonHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PokemonHolder {
            val view = layoutInflater.inflate(R.layout.list_pokemon_pokedex, parent,false)
            return PokemonHolder(view)
        }

        override fun getItemCount(): Int = galleryItems.size

        override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
            val galleryItem = galleryItems[position]
            val pokemonname = galleryItem.name.replace("-", "_")
            holder.bindGalleryItem(galleryItem)
            holder.bindImageUrl("https://projectpokemon.org/images/normal-sprite/${pokemonname}.gif")
            holder.bindPokemonName(galleryItem.name)
        }
    }

    companion object {
        fun newInstance() = PokedexFragment()
    }
}