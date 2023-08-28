package com.example.android101

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android101.databinding.ActivityFirstKotlnCalculateAppBinding
import com.example.android101.databinding.ActivityFirstStoryDateKotlinBinding

class FirstStoryDateKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityFirstStoryDateKotlinBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstStoryDateKotlinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPreferences =this.getSharedPreferences("com.example.android101", MODE_PRIVATE)
    }
    fun save(view : View){
        val takeAge =binding.editTextFirstKotlinageInput.text.toString().toIntOrNull()
        if(takeAge != null ) {
            binding.textViewFirstKotlinageShow.text = "Your age : ${takeAge}"
        }else {
            binding.textViewFirstKotlinageShow.text = "please write number"
        }


    }
    fun delete(view : View){

    }
}