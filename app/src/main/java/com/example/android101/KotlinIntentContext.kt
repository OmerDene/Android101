package com.example.android101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_kotlin_intent_context.*

class KotlinIntentContext : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_intent_context)


        val getIntent = intent
        val name = getIntent.getStringExtra("name")
        val surname = getIntent.getStringExtra("surname")
        nameShowText.text="İsminiz :" + name
        surnameShowText.text="soyisminiz : ${surname}"



    }
    fun back(view : View){
        val intent = Intent(applicationContext,KotlinIntent::class.java)
        startActivity(intent)
    }

}