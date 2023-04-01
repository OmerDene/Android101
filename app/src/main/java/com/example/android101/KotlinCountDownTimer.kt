package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_kotlin_count_down_timer.*

class KotlinCountDownTimer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_count_down_timer)
        object : CountDownTimer(10000,1000) {
            override fun onFinish() {
                countDownText.text = "Left: 0"
            }

            override fun onTick(millisUntilFinished: Long) {
                countDownText.text = "Left: ${millisUntilFinished/1000}"
            }

        }.start()
    }
}