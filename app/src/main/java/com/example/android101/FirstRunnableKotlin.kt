package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.android101.databinding.ActivityFirstKotlnCalculateAppBinding
import com.example.android101.databinding.ActivityKotlinRunnableBinding

class FirstRunnableKotlin : AppCompatActivity() {
    private lateinit var binding: ActivityKotlinRunnableBinding
    var number = 0
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinRunnableBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
    fun start(view : View){
        number = 0

        runnable = object : Runnable {
            override fun run() {

                number = number + 1
                binding.runnableText.text = "Time: $number"

                handler.postDelayed(this,1000)

            }

        }

        handler.post(runnable)

        binding.button15.isEnabled = false


    }
    fun stop(view : View){
        handler.removeCallbacks(runnable)
        number = 0
        binding.runnableText.text = "Time: 0"

        binding.button15.isEnabled = true

    }
}