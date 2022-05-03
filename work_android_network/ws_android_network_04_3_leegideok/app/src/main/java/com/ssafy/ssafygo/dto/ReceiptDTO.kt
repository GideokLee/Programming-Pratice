package com.ssafy.ssafygo.dto

data class ReceiptDTO(var menuName:String, var price: Int, var orderDate: String) {
    var id : Int = -1

    constructor(_id: Int, name : String, price: Int, date : String) : this(name,price,date){
        id = _id
    }

    override fun toString(): String {
        return "이름: $menuName \n 주문일자: $orderDate \n 가격: $price"
    }
}