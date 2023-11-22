package com.example.asynchronousflow3

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        sendNumber().collect{
            println("received $it")
        }
    }
}

fun sendNumber() = flowOf(1,2,3)
//= listOf(1,2,3).asFlow()
/*= flow {
    for (i in 1..10)
        emit(i)
}*/