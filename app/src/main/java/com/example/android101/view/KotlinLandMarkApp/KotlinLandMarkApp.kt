package com.example.android101.view.KotlinLandMarkApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.android101.KotlinIntentContext
import com.example.android101.R
import com.example.android101.databinding.ActivityKotlinLandMarkAppBinding
import com.example.android101.databinding.ActivityKotlinLandMarkDetailActiviyBinding

class KotlinLandMarkApp : AppCompatActivity() {
    private  lateinit var binding :ActivityKotlinLandMarkAppBinding
    val arraylist = ArrayList<Landmark>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinLandMarkAppBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pisa = Landmark("Pisa","Italy",R.drawable.pizza)
        val eifel = Landmark("Eifel","France",R.drawable.eyfel)
        val collesum = Landmark("Collesum","Italy",R.drawable.collesium)
        val londonBridge = Landmark("Pisa","UK",R.drawable.londonbridge)

        arraylist.add(pisa)
        arraylist.add(eifel)
        arraylist.add(collesum)
        arraylist.add(londonBridge)
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,arraylist.map { landmark ->landmark.name   })

        /*map : Burada map dönüstürmeye olanak tanır.Map kullanınca ugulamanın listView layoutunun oldugu yerde sırasıyla
        pisa ,eifel ,collesum,londonBridge textviewları gözükecek.Sonuc olarak listView bizim olusturdugumuz arrayList ve
        main aktivitede görünecek olan pisa ,eifel ,collesum,londonBridge isimleri birbirine baglanmıs oldu.*/

        binding.listView.adapter= adapter
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(applicationContext,KotlinLandMarkDetailActiviy::class.java)
            intent.putExtra("landmark",arraylist[position])
            //MySingleton.selectedLandmark = landmarkList[position]
            startActivity(intent)
        }


        }







    }
