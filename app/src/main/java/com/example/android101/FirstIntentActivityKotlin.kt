package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FirstIntentActivityKotlin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_intent_kotlin)
    }
}