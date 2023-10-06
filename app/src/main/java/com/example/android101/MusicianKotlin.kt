package com.example.android101

open class MusicianKotlin(namee: String, instrument: String, agee: Int) {


    var namee :String? = namee
        private set
        get
     var instrument : String? = instrument
        private set
        get


     var agee : Int? = agee
         get
        set

    private val bandName :String = "metallica"
    fun bandNameControl(password : String) :String {
        if(password== "atil"){
            return bandName

        }
        else {
            return "wrong password!"

        }


    }



}