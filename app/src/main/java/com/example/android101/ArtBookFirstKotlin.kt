package com.example.android101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.android101.databinding.ActivityArtBookFirstKotlinBinding
import com.example.android101.databinding.ActivityArtBookFirstKotlinDetailsBinding

class ArtBookFirstKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityArtBookFirstKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtBookFirstKotlinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.art_kotlin_first_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_art_item_kotlin){
            val intent = Intent(this@ArtBookFirstKotlin,ActivityArtBookFirstKotlinDetailsBinding::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}