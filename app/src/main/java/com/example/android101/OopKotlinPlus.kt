package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OopKotlinPlus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oop_kotlin_plus)
        var abc = OopUser("zeki",34)
        abc.name = "mehmet"
        println(abc.name)
    }
}