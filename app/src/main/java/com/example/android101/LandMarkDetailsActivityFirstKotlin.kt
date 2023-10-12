package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android101.databinding.ActivityLandMarkDetailsFirstKotlinBinding

class LandMarkDetailsActivityFirstKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityLandMarkDetailsFirstKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandMarkDetailsFirstKotlinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)





    }
}