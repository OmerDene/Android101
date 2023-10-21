package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display.Mode

class FirstKotlinSQLite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_kotlin_sqlite)
        try{
            val myDataBaseSql =this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)
            myDataBaseSql.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR, age INT)")

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}