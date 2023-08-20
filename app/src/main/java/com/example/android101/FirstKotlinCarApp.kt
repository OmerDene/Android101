package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.android101.databinding.ActivityFirstKotlinCarAppBinding


class FirstKotlinCarApp : AppCompatActivity() {
    lateinit var editTextBrand : EditText
    lateinit var editTextColor : EditText
    lateinit var editTextYear : EditText
    lateinit var textShowCarProperties :TextView
    private lateinit var binding : ActivityFirstKotlinCarAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityFirstKotlinCarAppBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        editTextBrand = findViewById(R.id.editTextCarBrand)
        editTextColor= findViewById(R.id.editTextColor)
        editTextYear = findViewById(R.id.editTextYear)
        textShowCarProperties = findViewById(R.id.textShowCarProperties)






    }
    fun clickCarPropertiesButton(carButton : View){
        var brand =editTextBrand.text.toString()
        var color =editTextColor.text.toString()
        var year =editTextYear.text.toString().toIntOrNull()
        textShowCarProperties.text = "${brand},${color},${year}"
        /*if(year !=null) {
            var car = FirstCarKotlin(brand,color,year)
            textShowCarProperties.text ="Brand : ${car.brand} Color : ${car.color} Year : ${car.year}"

        }else{
            textShowCarProperties.text = "You must fill  the area of year with only numbers"
        }*/





    }

}