package com.ssafy.ssafygo.dto

import java.io.Serializable

data class StoreReviewDTO(var content: String, var score: Int, var storeId: Int)
    : Serializable {

    var id : Int = -1

    constructor(_id: Int, content: String, score: Int, storeId: Int): this(content, score, storeId) {
        id = _id
    }

    override fun toString(): String {
        return "$content\n ${score}점"
    }
}