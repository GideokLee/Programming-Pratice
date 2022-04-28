package com.ssafy.network_01.coroutine.a_basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    this.launch {
        delay(300L)
        println("World!")
    }

    println("Hello,")
}