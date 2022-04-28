package com.ssafy.network_01.coroutine.c_global_scope

import kotlinx.coroutines.*


fun main() {
    test()
}

fun test() = runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L)
}