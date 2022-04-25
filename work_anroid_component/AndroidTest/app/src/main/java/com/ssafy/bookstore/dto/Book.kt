package com.ssafy.bookstore.dto

import java.io.Serializable

data class Book(var title: String, var author: String, var content: String, var price: Int, var date: String) : Serializable{
    var id = -1

    constructor(id:Int, title: String, author: String, content: String, price: Int, date: String) : this(title, author, content, price, date){
        this.id = id
    }

    override fun toString(): String {
        return "첵제목\t $title \n " +
                "책정보\t $content \n" +
                "작가이름 $author"
    }
}
