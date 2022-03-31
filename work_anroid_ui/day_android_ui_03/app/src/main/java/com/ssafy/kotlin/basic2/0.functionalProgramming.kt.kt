package com.ssafy.kotlin.basic2

fun main() {
    // 람다식
    val test: () -> Unit = {
        println("test")
    }

    function(test)
}

private fun function(f: () -> Unit) {
    f.invoke()
}

// 일반함수
fun sum(x1: Int, x2: Int): Int {
    return x1 + x2
}

// 람다함수
// 변수명:(람다식 datatype 타입) -> 리턴타입 = { 람다식 parameter  -> 구현/리턴 내용 }
val sum0:(Int, Int) -> Int = { x1: Int, x2: Int -> x1 + x2 }

val sum1:(Int, Int) -> Int = { x1: Int, x2: Int ->
    x1 + x2  // 화살표 뒤에 줄이 바꿔서 리턴
}

// 람다식이 여러줄일때는 마지막 라인이 리턴
val sum2:(Int, Int) -> Int = { x1: Int, x2: Int ->
    println(x1 + x2)
    x1 + x2  //마지막 줄 리턴
}

// 데이터 추론가능 매개변수 데이터타입 생략
val sum3:(Int, Int) -> Int = { x1, x2 ->
    x1 + x2
}

// 데이터 추론가능 람다식 선언 데이터타입 생략
val sum4 = { x1: Int, x2: Int -> x1 + x2 }

// 에러 : 데이터 추론이 불가능함.
//val sum5 = { x1, x2 -> x1 + x2 }