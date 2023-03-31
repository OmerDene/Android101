package com.example.android101

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_basic_atm.*

@SuppressLint()
class KotlinBasicATM : AppCompatActivity() {
    private var remainder : Int = 1000
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_atm)

        sharedPreferences = getSharedPreferences("com.example.android101", MODE_PRIVATE)
        val sharedShow =sharedPreferences.getInt("money",0)

        if (sharedShow!=0) {
            val result = "Mevcut para:${sharedShow}"
            sonucText.text = result
        }
    }


    fun withdraw() {
        val tutar = tutarText.text.toString().toIntOrNull()

        if(tutar!=null){
            sonucText.text="Mevcut bakiye:${remainder-tutar}"
            sharedPreferences.edit().putInt("money",tutar).apply()
        }
        if (tutar != null) {
            if(tutar > remainder){

                sonucText.text ="Yetersiz Bakiye"
            }
        }
    }

    fun invest() {
        val tutar = tutarText.text.toString().toIntOrNull()

        if (tutar!=null) {
            sonucText.text="Mevcut bakiye:${remainder+tutar}"
            sharedPreferences.edit().putInt("money",tutar).apply()
        }

    }
    fun learnMoney() {
        sonucText.text = "Mevcut Bakiye: " + remainder
    }
}