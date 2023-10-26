package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android101.databinding.ActivityArtBookFirstKotlinDetailsBinding

class ArtBookFirstKotlinDetails : AppCompatActivity() {
    private lateinit var binding : ActivityArtBookFirstKotlinDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtBookFirstKotlinDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun save (view : View){


    }
    fun selectedPicture(view : View){

    }
}