package com.example.android101

class OopUser {
    var name :String? = null
    var age : Int? = null

constructor(nameInput:String,ageInput:Int){

    this.name = nameInput
    this.age = ageInput
    println("ben")
}
    init {
        println("init")
    }

}