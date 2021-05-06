package com.example.pokedexv4

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var pokedex:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pokedex = findViewById(R.id.pokedex)
        pokedex.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.click)
            mediaPlayer.start()
            val intent = Intent(this, PokedexActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}