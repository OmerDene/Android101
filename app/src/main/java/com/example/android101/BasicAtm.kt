package com.example.android101

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_basic_atm.*

class BasicAtm : AppCompatActivity() {
    var remainder : Int = 1000
    lateinit var sharedprefrences : SharedPreferences




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_atm)


        sharedprefrences=this.getSharedPreferences("com.example.android101", MODE_PRIVATE)
        var sharedShow =sharedprefrences.getInt("money",0)
        if(sharedShow!=0){
            sonucText.text ="Mevcut para:${sharedShow}"
        }
    }






    fun withdraw(view : View){
        var tutar =tutarText.text.toString().toIntOrNull()
        if(tutar!=null){
            sonucText.text="Mevcut bakiye:${remainder-tutar}"
            sharedprefrences.edit().putInt("money",tutar).apply()
        }
        if (tutar != null) {
            if(tutar > remainder){

                sonucText.text ="Yetersiz Bakiye"
            }
        }

    }
    fun invest(view : View){
        var tutar =tutarText.text.toString().toIntOrNull()
        if(tutar!=null){
            sonucText.text="Mevcut bakiye:${remainder+tutar}"
        }

    }
    fun learnMoney(view : View){
        sonucText.text = "Mevcut Bakiye: " + remainder

    }
}