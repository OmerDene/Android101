package com.example.android101

 class Piano :HouseDecor,Instrument {
    var brand : String? = null
    var digital : Boolean? = null
     override var roomName: String
         get() = "kitchen"
         set(value) {}

 }