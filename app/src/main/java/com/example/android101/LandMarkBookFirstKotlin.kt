package com.example.android101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.recyclerViewLandmark.layoutManager = LinearLayoutManager(this)
        val landmarkAdapter = LandmarkAdapterKotlin(landMarkList)
        binding.recyclerViewLandmark.adapter = landmarkAdapter
        /*val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,landMarkList.map { landmark -> landmark.name })

        binding.listViewLadmark.adapter = adapter

        binding.listViewLadmark.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(applicationContext,LandMarkDetailsActivityFirstKotlin::class.java)
            intent.putExtra("landmark",landMarkList[position])
            //MySingleton.selectedLandmark = landmarkList[position]
            startActivity(intent)
*/
    }
}
