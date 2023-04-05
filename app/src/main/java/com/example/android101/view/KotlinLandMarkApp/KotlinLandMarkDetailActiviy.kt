package com.example.android101.view.KotlinLandMarkApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android101.R
import com.example.android101.databinding.ActivityKotlinLandMarkDetailActiviyBinding

class KotlinLandMarkDetailActiviy : AppCompatActivity() {
    private  lateinit var binding : ActivityKotlinLandMarkDetailActiviyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinLandMarkDetailActiviyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        val landmark = intent.getSerializableExtra("landmark",) as Landmark
        binding.landMarkNameText.text = landmark.name
        binding.landMarkCountryText.text = landmark.country
        binding.imageView12.setImageResource(landmark.image)




    }
}