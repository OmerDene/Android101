package com.example.android101

import android.content.Intent
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
        val intent = Intent(applicationContext,NextIntentFirtsKotlin::class.java)
        intent.putExtra("name",binding.editTextFirstScreen.text.toString())
        startActivity(intent)
        finish()


    }
}