package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android101.databinding.ActivityFirstKotlnCalculateAppBinding

class FirstKotlnCalculateApp : AppCompatActivity() {
    private lateinit var binding: ActivityFirstKotlnCalculateAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstKotlnCalculateAppBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun sum(sum : View) {
        var number1 =binding.firstKotlinCalculatenumber1.text.toString().toDoubleOrNull()
        var number2 =binding.firstKotlinCalculatenumber2.text.toString().toDoubleOrNull()



        if(number1!= null  && number2 != null  ){
            val result = number1 +number2

            binding.firstKotlinCalculateResultText.text ="Result : ${result}"
        }else{
            binding.firstKotlinCalculateResultText.text = "Enter number"
        }




    }
    fun sub(sub : View) {
        var number1 =binding.firstKotlinCalculatenumber1.text.toString().toDoubleOrNull()
        var number2 =binding.firstKotlinCalculatenumber2.text.toString().toDoubleOrNull()



        if(number1!= null  && number2 != null  ){
            val result = number1 -number2

            binding.firstKotlinCalculateResultText.text ="Result : ${result}"
        }else{
            binding.firstKotlinCalculateResultText.text = "Enter number"
        }

    }
    fun multi(multi : View) {
        var number1 =binding.firstKotlinCalculatenumber1.text.toString().toDoubleOrNull()
        var number2 =binding.firstKotlinCalculatenumber2.text.toString().toDoubleOrNull()



        if(number1!= null  && number2 != null  ){
            val result = number1 *number2

            binding.firstKotlinCalculateResultText.text ="Result : ${result}"
        }else{
            binding.firstKotlinCalculateResultText.text = "Enter number"
        }

    }
    fun div(div : View) {
        var number1 =binding.firstKotlinCalculatenumber1.text.toString().toDoubleOrNull()
        var number2 =binding.firstKotlinCalculatenumber2.text.toString().toDoubleOrNull()



        if(number1!= null  && number2 != null  ){
            val result = number1 /number2

            binding.firstKotlinCalculateResultText.text ="Result : ${result}"
        }else{
            binding.firstKotlinCalculateResultText.text = "Enter number"
        }

    }

}