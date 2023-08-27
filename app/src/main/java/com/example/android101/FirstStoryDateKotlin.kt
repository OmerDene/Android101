package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android101.databinding.ActivityFirstKotlnCalculateAppBinding
import com.example.android101.databinding.ActivityFirstStoryDateKotlinBinding

class FirstStoryDateKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityFirstStoryDateKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstStoryDateKotlinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun save(view : View){

    }
    fun delete(view : View){

    }
}