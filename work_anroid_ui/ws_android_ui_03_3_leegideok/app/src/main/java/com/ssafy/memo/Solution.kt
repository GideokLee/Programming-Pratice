package com.ssafy.memo

import java.util.*

class Solution {

}

class Sort(var i :Int, var j :Int, var d :Int)

fun main(){
    val sc = Scanner(System.`in`)

    val T = sc.next().toInt()

    for(test_case in 1..T){
        val N = sc.next().toInt()
        val M = sc.next().toInt()

        var input =  Array<IntArray>( N , {IntArray(N )})
        val list = mutableListOf<Sort>();
        for(i in 1..M){
            val i = sc.next().toInt()
            val j = sc.next().toInt()
            val d = sc.next().toInt()
            input[i][j] = 1
            list.add(Sort(i,j,d))
        }
        val move_i = listOf(0,1,-1,0,0)
        val move_j = listOf(0,0,0,-1,1)
        var result = M

        for(i in 3 downTo 1){
            for(j in 0..M-1){
                if(list[j].d != 0){
                    val curr_i = list[j].i + (i * move_i[list[j].d])
                    val curr_j = list[j].j + (i * move_j[list[j].d])

                    if(curr_i < 0 || curr_i >= N){
                    }else if(curr_j < 0 || curr_j >= N){
                    }else if(input[curr_i][curr_j] == 1){
                    }else{
                        input[curr_i][curr_j] = 1
                        input[list[j].i][list[j].j] = 0
                        continue
                    }
                    list[j].d = 0
                    result--
                    input[list[j].i][list[j].j] = 0
                }
            }
        }
        println("#" + test_case.toString() + " " + result.toString())



    }


}