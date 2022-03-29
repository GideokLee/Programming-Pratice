package com.ssafy.memo

import java.io.Serializable

/*data */class MemoItem(var title: String, var content: String, var date: String) :Serializable{

    // alt + insert
    override fun toString(): String {
        return "MemoItem(title='$title', content='$content', date='$date')"
    }
}