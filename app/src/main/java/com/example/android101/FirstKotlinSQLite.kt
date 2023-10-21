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
            myDataBaseSql.execSQL("INSERT INTO musicians (name, age) VALUES ('James',50)")
            val cursor = myDataBaseSql.rawQuery("SELECT * FROM musicians",null)
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            while (cursor.moveToNext()) {
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIx))

            }

            cursor.close()


        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}