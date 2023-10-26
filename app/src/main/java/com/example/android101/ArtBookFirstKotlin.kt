package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}