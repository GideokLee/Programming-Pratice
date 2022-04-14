package com.ssafy.content.provider.note

import android.net.Uri

object Notes {
    const val AUTHORITY = "com.ssafy.content.provider.note"
    val CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/notes")

    const val DEFAULT_SORT_ORDER = "title"
    const val ID = "_id"
    const val TITLE = "title"
    const val BODY = "body"
}


