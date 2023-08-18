package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_first_kotlin_car_app.*

class FirstKotlinCarApp : AppCompatActivity() {
    lateinit var editTextBrand : EditText
    lateinit var editTextColor : EditText
    lateinit var editTextYear : EditText
    lateinit var textShowCarProperties :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_kotlin_car_app)
        editTextBrand = findViewById(R.id.editTextCarBrand)
        editTextColor= findViewById(R.id.editTextColor)
        editTextYear = findViewById(R.id.editTextYear)
        textShowCarProperties = findViewById(R.id.textShowCarProperties)





    }
    fun clickCarPropertiesButton(carButton : View){
        var brand =editTextBrand.text.toString()
        var color =editTextColor.text.toString()
        var year =editTextYear.text.toString()
        var car = FirstCarKotlin(brand,color,year)
        textShowCarProperties.text ="Brand :${car.brand} Color :${car.color} Year :${car.year}"



    }

}