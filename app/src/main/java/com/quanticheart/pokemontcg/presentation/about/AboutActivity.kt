package com.quanticheart.pokemontcg.presentation.about

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.pokemontcg.databinding.ActivityMainBinding
import com.quanticheart.pokemontcg.presentation.home.listCards.ListPokemonsActivity

class AboutActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btPokedex.setOnClickListener {
            startActivity(Intent(this, ListPokemonsActivity::class.java))
        }
    }
}