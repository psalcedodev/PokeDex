package com.example.pokedexv4

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
private lateinit var mediaPlayer: MediaPlayer
class PokedexActivity : AppCompatActivity() {

    private lateinit var homepageButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        homepageButton = findViewById(R.id.homepage)

        homepageButton.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.click)
            mediaPlayer.start()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, PokedexFragment.newInstance())
                .commit()
        }
    }
}