package com.example.android101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android101.databinding.ActivityLandMarkDetailsFirstKotlinBinding

class LandMarkDetailsActivityFirstKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityLandMarkDetailsFirstKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandMarkDetailsFirstKotlinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent
        val landmark = intent.getSerializableExtra("landmark") as LandMarkFirstKotlin
        binding.landMarkNameTextWiew.text = landmark.name
        binding.landMarkCountryTextView.text = landmark.country
        binding.landMarkFirstKotlinImage.setImageResource(landmark.image)








    }

}