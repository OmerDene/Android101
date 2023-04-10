package com.example.android101.KotlinArtBook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android101.R
import com.example.android101.databinding.ActivityKotlinArtBookDetailBinding
import com.example.android101.databinding.ActivityKotlinLandMarkDetailActiviyBinding

class KotlinArtBookDetail : AppCompatActivity() {
    private  lateinit var binding : ActivityKotlinArtBookDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinArtBookDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    fun save(view : View){


    }
    fun fotoSec(view: View){

    }
}