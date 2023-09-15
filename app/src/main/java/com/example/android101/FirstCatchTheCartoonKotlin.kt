package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android101.databinding.ActivityFirstCatchTheCartoonKotlinBinding
import com.example.android101.databinding.ActivityKotlinRunnableBinding

class FirstCatchTheCartoonKotlin : AppCompatActivity() {
    private lateinit var binding: ActivityFirstCatchTheCartoonKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFirstCatchTheCartoonKotlinBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }
}