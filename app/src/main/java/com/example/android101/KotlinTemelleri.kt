package com.example.android101

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class KotlinTemelleri : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
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

        //Boolean
        println("------- Boolean -------")

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


        val myArray = arrayOf("James","Kirk","Rob","Lars")

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











    }





}
/*
Not: var x = 5 gibi bir ifade yazarsak burda 5 i x değişkenine atamış oluruz ve bunu ileriki satırlarda
örneğin var x = 7 ile değiştirebiliriz burada herhangi bir sorun yok. Fakat val y = 8 gibi bir ifade yazarsak
buradaki "val" sabit olur bunu değiştiremeyiz.Yani var değiştirilebilir val ise değiştirilemez.
Örneğin val a : Int = 1 yazarsak burada a isminde bir integer sınıfına ait degişken oluşturmuş oluruz ve
buraya int ile 1 dışında başka bir değer atayamayız.Ama var a : Int = 1 şeklinde oluştursaydık bunu a degişkenini
değiştirebilirdik ama sadece başka bir integer ile degiştirebilirdik yani burayada başta int olarak define ettiğimizden
dolayı tutupta String bir ifade ile değiştiremeyiz.

Not:Arrayof ta yani dizilerde diziyi ilk nasıl initialization edersen o kadar eleman orada kalacaktır.
 */
