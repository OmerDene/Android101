package com.example.android101

class DogKotlin :AnimalKotlin(){
    override fun sign(){
        println("Dog class")
    }
    fun test(){
        super.sign()
    }
}