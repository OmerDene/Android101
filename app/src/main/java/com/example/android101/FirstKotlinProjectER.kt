package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FirstKotlinProjectER : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_kotlin_project_er)
        var x = 5
        var y = 4

        println(x * y)

        var age = 35

        val result = age / 7 * 5

        println(result)

        //Defining
        var myInteger : Int

        // Initialize
        myInteger = 10

        myInteger = 20

        val a : Int = 23

        println(a/2)

        //Long

        var myLong : Long = 100


        //Double & Float

        println("-------Double & Float -------")

        val pi = 3.14


        println(pi * 2.0)

        val myFloat = 3.14f

        println (myFloat * 2)

        var myAge : Double
        myAge = 23.0
        println(myAge/2)

        //camelCase
        //snake_case


        //String
        println("------- String -------")

        val myString = "atil samancioglu"
        val name = "James"
        val surname = "Hetfield"

        val fullname = name + " " + surname

        println(fullname)

        val larsName : String = "Lars"

        println(myString.capitalize())
        var myBoolean : Boolean = true
        myBoolean = false

        var isAlive = true

        // <
        // >
        // <=
        // >=
        // ==
        // !=
        // && -> AND
        // || -> OR

        println(3 < 5)
        println(6 < 3)
        println(3 == 3)
        println ( 4 != 5)


        //Conversion
        println("------- Conversion -------")

        var myNumber = 5
        var myLongNumber = myNumber.toLong()
        println(myLongNumber)

        var input = "10"
        var inputInteger = input.toInt()
        println(inputInteger * 2)

        //Collections

        //Arrays

        println("------- Array -------")

    }


}