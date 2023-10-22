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

            //myDataBaseSql.execSQL("INSERT INTO musicians (name, age) VALUES ('James',50)")
            //myDataBaseSql.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars',60)")
            //myDataBaseSql.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk',55)")

            //myDataBaseSql.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'")
            //myDataBaseSql.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3")

            myDataBaseSql.execSQL("DELETE FROM musicians WHERE name = 'Lars'")


            //val cursor = myDataBaseSql.rawQuery("SELECT * FROM musicians WHERE id = 3",null)

            //val cursor = myDataBaseSql.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null)

            val cursor = myDataBaseSql.rawQuery("SELECT * FROM musicians",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) {
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIx))
                println("Id: " + cursor.getInt(idIx))
            }

            cursor.close()


        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}