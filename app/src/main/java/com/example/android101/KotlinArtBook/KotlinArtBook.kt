package com.example.android101.KotlinArtBook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android101.R
import com.example.android101.databinding.ActivityKotlinArtBookBinding
import com.example.android101.databinding.ActivityKotlinArtBookDetailBinding
import com.example.android101.java.ArtAdapter

class KotlinArtBook : AppCompatActivity() {
    private lateinit var artList: ArrayList<Art>
    private lateinit var binding: ActivityKotlinArtBookBinding
    private lateinit var artAdapter : Artadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinArtBookBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        artList=ArrayList<Art>()

        artAdapter = Artadapter(artList)
        binding.kotlinArtRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.kotlinArtRecyclerView.adapter =artAdapter

        try{
            val database =this.openOrCreateDatabase("Arts", MODE_PRIVATE,null)
            val cursor =database.rawQuery("SELECT * FROM arts",null)
            val artnameIx =cursor.getColumnIndex("artname")
            val idIx =cursor.getColumnIndex("id")
            while (cursor.moveToNext()){
                val name =cursor.getString(artnameIx)
                val id = cursor.getInt(idIx)
                val art = Art(name,id)
                artList.add(art)
            }
            artAdapter.notifyDataSetChanged()
            cursor.close()




        }catch (e : Exception){
            e.printStackTrace()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.kotlin_art_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_kotlin_art) {
            val intent = Intent(this,KotlinArtBookDetail::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}