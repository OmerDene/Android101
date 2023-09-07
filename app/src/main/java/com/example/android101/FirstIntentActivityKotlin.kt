package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android101.databinding.ActivityFirstIntentKotlinBinding

class FirstIntentActivityKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityFirstIntentKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstIntentKotlinBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
    }
    fun next(view : View){


    }
}