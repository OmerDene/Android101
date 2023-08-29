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
        val agefromback = sharedPreferences.getInt("age",0)
        binding.textViewFirstKotlinageShow.text = "your age: ${agefromback}"
    }
    fun save(view : View){
        val takeAge =binding.editTextFirstKotlinageInput.text.toString().toIntOrNull()
        if(takeAge != null ) {
            binding.textViewFirstKotlinageShow.text = "Your age : ${takeAge}"
            sharedPreferences.edit().putInt("age",takeAge).apply()
        }else {
            binding.textViewFirstKotlinageShow.text = "please write number"
        }


    }
    fun delete(view : View){
        val deleteage = sharedPreferences.edit().remove("age").apply()
        binding.textViewFirstKotlinageShow.text ="Your age : ${deleteage}"


    }
}