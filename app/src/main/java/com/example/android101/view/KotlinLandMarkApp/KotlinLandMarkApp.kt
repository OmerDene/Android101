package com.example.android101.view.KotlinLandMarkApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android101.KotlinIntentContext
import com.example.android101.R
import com.example.android101.databinding.ActivityKotlinLandMarkAppBinding
import com.example.android101.databinding.ActivityKotlinLandMarkDetailActiviyBinding

class KotlinLandMarkApp : AppCompatActivity() {
    private  lateinit var binding :ActivityKotlinLandMarkAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinLandMarkAppBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }
}