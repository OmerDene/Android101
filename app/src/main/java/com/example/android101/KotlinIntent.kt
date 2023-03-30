package com.example.android101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_kotlin_intent.*

class KotlinIntent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_intent)
    }
    fun next(view : View){
        val intent = Intent(applicationContext,KotlinIntentContext::class.java)
        intent.putExtra("name",intentNameText.text.toString())
        intent.putExtra("surname",intentSurnameText.text.toString())
        startActivity(intent)


    }
}