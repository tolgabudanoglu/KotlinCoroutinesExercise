package com.example.asynchronousflow3

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() {

    runBlocking {
        val numbersFlow = sendNewNumbers()
        println("flow hasnt started yet")
        println("flow  started ")
        withTimeoutOrNull(1000L){
            numbersFlow.collect{ println(it) }
        }

    }

}
fun sendNewNumbers() = flow {
    listOf(1,2,3).forEach{
        delay(400L)
        emit(it)
    }
}