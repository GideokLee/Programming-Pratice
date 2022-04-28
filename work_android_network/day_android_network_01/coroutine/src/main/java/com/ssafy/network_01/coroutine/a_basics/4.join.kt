package com.ssafy.network_01.coroutine.a_basics

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val job = GlobalScope.launch {
        delay(300L)
        println("World!")
    }

    println("Hello,")
    job.join()
}