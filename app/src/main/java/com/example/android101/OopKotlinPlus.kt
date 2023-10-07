package com.example.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OopKotlinPlus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oop_kotlin_plus)
        var abc = OopUser("zeki",34)
        abc.name = "mehmet"
        //println(abc.name)
        var tarkan = MusicianKotlin("tarkan","guitar",21)
        //println(tarkan.instrument)
       //println(tarkan.bandNameControl("atil"))
        //println(tarkan.bandNameControl("mahmut"))
        val omerbey = SuperMusicianKotlin("omer","bass guitar",13)
        //println(omerbey.instrument)
        //println(omerbey.agee)
        //omerbey.sing()
        //println(omerbey.bandNameControl("atil"))
        val matematik = Mathematics()
        //println(matematik.sum())
       // println(matematik.sum(10,5))
       // println(matematik.sum(2,4,7))
        val animal = AnimalKotlin()
        val dog = DogKotlin()
        //println(animal.sign())
        //println(dog.test())
        //println(dog.sign())
        val userdeneme = OopUser("ahmet",16)
        //userdeneme.information()
        var myPiano = Piano()
        myPiano.brand= "Yamaha"
        myPiano.digital = true
        println(myPiano.brand)
        println(myPiano.digital)
        println(myPiano.roomName)
        myPiano.info()




    }
}