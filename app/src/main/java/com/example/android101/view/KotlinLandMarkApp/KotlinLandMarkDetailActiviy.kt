package com.example.android101.view.KotlinLandMarkApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android101.R
import com.example.android101.databinding.ActivityKotlinLandMarkDetailActiviyBinding

class KotlinLandMarkDetailActiviy : AppCompatActivity() {
    private  lateinit var binding : ActivityKotlinLandMarkDetailActiviyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinLandMarkDetailActiviyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}