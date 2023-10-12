package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android101.databinding.ActivityLandMarkBookFirstKotlinBinding
import com.example.android101.databinding.ActivityLandMarkDetailsFirstKotlinBinding

class LandMarkBookFirstKotlin : AppCompatActivity() {
    private lateinit var binding : ActivityLandMarkBookFirstKotlinBinding
    private lateinit var landMarkList :ArrayList<LandMarkFirstKotlin>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandMarkBookFirstKotlinBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        landMarkList =ArrayList<LandMarkFirstKotlin>()

        val pisa = LandMarkFirstKotlin("pisa","italy",R.drawable.pizza)
        val eifel = LandMarkFirstKotlin("eifel","france",R.drawable.eyfel)
        val londonBringe = LandMarkFirstKotlin("londonBridge","england",R.drawable.londonbridge)
        val colesium= LandMarkFirstKotlin("colesium","italy",R.drawable.collesium)
        landMarkList.add(pisa)
        landMarkList.add(eifel)
        landMarkList.add(londonBringe)
        landMarkList.add(colesium)


    }
}