package com.ssafy.cleanstore.dto

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class Stuff (var name: String?, var count: Int, var regDate : Long) : Serializable {
    var id = -1

    constructor(id: Int, name: String, count: Int, regDate: Long) : this(name, count, regDate) {
        this.id = id
    }

    override fun toString(): String {
        val formatter = SimpleDateFormat("yy / M / dd")
        formatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        val dateString = formatter.format(regDate)
        return "물품 : $name -> 수량 : $count, 입고일 : " + dateString
    }
}