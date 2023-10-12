package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android101.databinding.ActivityLandMarkBookFirstKotlinBinding
import com.example.android101.databinding.ActivityLandMarkDetailsFirstKotlinBinding

class LandMarkBookFirstKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityLandMarkBookFirstKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandMarkBookFirstKotlinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}