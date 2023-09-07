package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android101.databinding.ActivityNextIntentFirtsKotlinBinding

class NextIntentFirtsKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityNextIntentFirtsKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextIntentFirtsKotlinBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)

    }

}