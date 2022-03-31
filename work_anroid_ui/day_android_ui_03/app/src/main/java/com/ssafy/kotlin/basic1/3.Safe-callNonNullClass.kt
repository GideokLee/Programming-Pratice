package com.ssafy.kotlin.basic1

fun main() {

//    ignoreNulls(null)

    val x: String? = null
    println(strLenSafe(x))
    println(strLenSafe("abc"))

    var str: String? = "Hello Kotlin"
    str = null
    println("str : &str length : ${str?.length ?: -1}")
}

fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

fun strLenSafe(s: String?): Int = if (s != null) s.length else 0

fun getName(str: String?) {
    val name = str ?: "Unknown"
}
