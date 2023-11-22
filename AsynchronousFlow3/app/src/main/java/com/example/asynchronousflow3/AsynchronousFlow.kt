package com.example.asynchronousflow3

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun main(){
    println("receiving prime numebr")
    sendPrimes().collect{
        println("Receivid prime number $it")
    }
    println("fisinshed receiving numbers")
}

fun sendPrimes():Flow<Int> = flow {
    val primeLisy = listOf<Int>(2,3,5,7,11,13,17,19,23,29)
    primeLisy.forEach{
        delay(it*100L)
        emit(it)
    }
}