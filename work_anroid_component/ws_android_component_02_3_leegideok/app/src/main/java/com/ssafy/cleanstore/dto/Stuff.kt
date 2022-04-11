package com.ssafy.cleanstore.dto

class Stuff(var name : String, var volume : Int) {

    override fun toString() : String{
        return "물품 : $name -> 수랑 : $volume"
    }
}