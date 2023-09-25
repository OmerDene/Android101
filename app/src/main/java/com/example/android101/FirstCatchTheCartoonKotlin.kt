package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android101.databinding.ActivityFirstCatchTheCartoonKotlinBinding
import com.example.android101.databinding.ActivityKotlinRunnableBinding

class FirstCatchTheCartoonKotlin : AppCompatActivity() {
    private lateinit var binding: ActivityFirstCatchTheCartoonKotlinBinding
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFirstCatchTheCartoonKotlinBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }
    fun increaseScore(view : View){
        score++
        binding.scoreText.text = "Score : ${score}"

    }
}