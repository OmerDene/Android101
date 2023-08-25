package com.example.android101

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.android101.databinding.ActivityFirstKotlinProjectErBinding

class FirstKotlinProjectER : AppCompatActivity() {
    private lateinit var binding: ActivityFirstKotlinProjectErBinding
    lateinit var sumtextView: TextView
    lateinit var sharedPreferences : SharedPreferences
    var ageFromPreferences : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstKotlinProjectErBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sum(2420,7585)
        var simpSon = FirstSimpson("mahmut",25,"kripto")

        simpSon.omer(49)
        println(simpSon.height)
        var myString : String? = null
        myString = "test"
        println(myString)
        //SharedPrefences Initialize
        sharedPreferences = this.getSharedPreferences("com.atilsamancioglu.storingdata",
            Context.MODE_PRIVATE)

        ageFromPreferences = sharedPreferences.getInt("age",-1)

        if (ageFromPreferences == -1) {
            binding.firstKotlinErTextview.text = "Your Age: "
        } else {
            binding.firstKotlinErTextview.text = "Your Age: $ageFromPreferences"
        }
        //Nullability

        var myAge : Int? = null
        //myAge = 50
        // !! ?

        //println(myAge!! * 10)
        //2) safe call

        println(myAge?.minus(10))

        //3)

        if(myAge != null) {
            println(myAge.minus(10))
        } else {
            println("myAge is null")
        }


        //4) elvis operator

        println(myAge?.minus(10) ?: -10)

        //5)
        myAge?.let {
            println(it * 10)
        }


        /*  var x = 5
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

          println("------- Array -------")*/
       /* val myArray = arrayOf("James","Kirk","Rob","Lars")

        //index
        println(myArray[0])
        myArray[0] = "James Hetfield"
        println(myArray[0])

        myArray.set(1,"Kirk Hammett")
        println(myArray[1])

        val numberArray = arrayOf(1,2,3,4,5)
        println(numberArray[3])

        val myNewArray = doubleArrayOf(1.0,2.0,3.0)

        val mixedArray = arrayOf("Atil",5)
        println(mixedArray[0])
        println(mixedArray[1])


        //List - ArrayList

        println("------- List -------")

        val myMusician = arrayListOf<String>("James","Kirk")
        myMusician.add("Lars")
        println(myMusician[2])
        myMusician.add(0,"Rob")
        println(myMusician[0])

        val myArrayList = ArrayList<Int>()
        myArrayList.add(1)
        myArrayList.add(200)

        val myMixedArrayList = ArrayList<Any>()
        myMixedArrayList.add("Atil")
        myMixedArrayList.add(12.25)
        myMixedArrayList.add(true)

        println(myMixedArrayList[0])
        println(myMixedArrayList[1])
        println(myMixedArrayList[2])

        val myExampleArray = arrayOf(1,1,2,3,4)

        println("element 1: ${myExampleArray[0]}")
        println("element 2: ${myExampleArray[1]}")

        val mySet = setOf<Int>(1,1,2,3)

        println(mySet.size)

        //For Each

        mySet.forEach { println(it) }

        val myStringSet = HashSet<String>()
        myStringSet.add("Atil")
        myStringSet.add("Atil")
        println(myStringSet.size)

        //Map - HashMap

        println("------- Map -------")

        //Key - Value

        val fruitArray = arrayOf("Apple", "Banana")
        val caloriesArray = arrayOf(100,150)

        println("${fruitArray[0]} : ${caloriesArray[0]}")

        val fruitCalorieMap = hashMapOf<String,Int>()
        fruitCalorieMap.put("Apple",100)
        fruitCalorieMap.put("Banana",150)
        println(fruitCalorieMap["Apple"])

        val myHashMap = HashMap<String, String>()

        val myNewMap = hashMapOf<String,Int>("A" to 1,"B" to 2,"C" to 3)
        println(myNewMap["C"])
        var m = 5
        println(m)
        m = m + 1
        println(m)
        m++
        println(m)
        m--
        println(m)

        var n = 7

        println(n > m)

        // &&
        // ||

        println(n > m && 1 > 2)
        println(n > m || 1 > 2)

        println(10+2)
        println(10-2)
        println(10*2)
        println(10/2)

        //Remainder
        println(10%4)

        //If Control
        println("------- If Control -------")


        val myNumberAge = 52

        if (myNumberAge < 30) {
            println("< 30")
        } else if (myNumberAge >= 30 && myNumberAge < 40) {
            println("30 - 40")
        } else if (myNumberAge >= 40 && myNumberAge < 50) {
            println("40 - 50")
        } else {
            println(">=50")
        }
        val day = 3
        var dayString = ""

        /*
        if (day == 1) {
            dayString = "Monday"
        } else if ( day == 2) {
            dayString = "Tuesday"
        } else if (day == 3) {
            dayString = "Wednesday"
        }

        println(dayString)

         */

        when(day) {

            1 -> dayString = "Monday"
            2 -> dayString = "Tuesday"
            3 -> dayString = "Wednesday"
            else -> dayString = ""
        }

        println(dayString)
        val myArrayofNumbers = arrayOf(12,15,18,21,24,27,30,33)
        val q = myArrayofNumbers[0] / 3 * 5
        println(q)

        for (num in myArrayofNumbers) {
            val z = num / 3 * 5
            println(z)
        }

        for (i in myArrayofNumbers.indices) {
            val qz = myArrayofNumbers[i] / 3 * 5
            println(qz)
        }

        for (b in 0..9) {
            println(b)
        }

        val myStringArrayList = ArrayList<String>()
        myStringArrayList.add("Atil")
        myStringArrayList.add("Samancioglu")
        myStringArrayList.add("Bar")

        for (str in myStringArrayList) {
            println(str)
        }

        myStringArrayList.forEach { println(it) }

        //While Loop

        println("------- While Loop -------")


        var j = 0

        while (j < 10) {
            println(j)
            j = j + 1
        }

*/
    }
    fun sum(a:Int ,b:Int) {
        val sumTextview = findViewById<TextView>(R.id.firstKotlinErTextview)
        sumTextview.text = "Merhaba Sonucunuz : ${a+b}"
    }
    fun buttonClicked(omer : View){
        sumtextView.text = "button clicked"


    }





}