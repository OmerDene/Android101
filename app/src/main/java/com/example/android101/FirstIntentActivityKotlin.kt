package com.example.android101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.android101.databinding.ActivityFirstIntentKotlinBinding

class FirstIntentActivityKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityFirstIntentKotlinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstIntentKotlinBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        object : CountDownTimer(10000,1000) {
            override fun onFinish() {
                binding.CountDownTimerTextview.text = "Left: 0"
            }

            override fun onTick(millisUntilFinished: Long) {
                binding.CountDownTimerTextview.text = "Left: ${millisUntilFinished/1000}"
            }

        }.start()
    }

    override fun onResume() {
        super.onResume()

        println("on resume called")

    }


    override fun onPause() {
        super.onPause()

        println("on pause called")

    }

    override fun onStop() {
        super.onStop()

        println("on stop called")

    }

    override fun onDestroy() {
        super.onDestroy()

        println("on destroy called")

    }
    fun next(view : View){
        val intent = Intent(applicationContext,NextIntentFirtsKotlin::class.java)
        intent.putExtra("name",binding.editTextFirstScreen.text.toString())
        startActivity(intent)
        finish()


    }
}