package com.example.android101

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_kotlin_neccesary.*

class KotlinNeccesaryShared : AppCompatActivity() {
    lateinit var sharedPrefrences : SharedPreferences
    var sharedTry :Int? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_neccesary)



        sharedPrefrences =this.getSharedPreferences("com.example.android101", MODE_PRIVATE)
        sharedTry =sharedPrefrences.getInt("age",0)
        if(sharedTry==0){
            ageShowText.text = "Yasınız yok :0"

        }else
            ageShowText.text="Yasınız: ${sharedTry}"
    }

    fun save(view : View){
        val ageinputtext = ageİnputText.text.toString().toIntOrNull()
        if(ageinputtext!=null){
            ageShowText.text = "Yasınız : ${ageinputtext}"
            sharedPrefrences.edit().putInt("age",ageinputtext).apply()

        }else
            ageShowText.text = "Yas yok"





    }
    fun delete(view : View){
        sharedTry =sharedPrefrences.getInt("age",0)
        if(sharedTry!=0){
            sharedPrefrences.edit().remove("age",).apply()
            ageShowText.text = "Yasınız silindi: "
        }

    }
}