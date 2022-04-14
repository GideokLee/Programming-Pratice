package com.ssafy.content.resolver.note

data class NotesDto(val TITLE:String= "title", val BODY:String="body") {

    var _ID : Long = -1

    constructor(id:Long, title:String, content:String): this(title, content) {
        _ID = id
    }
}