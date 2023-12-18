package com.example.asynchronousflow3

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        mapOperator()
        filterOperator()
        transformOpertaotr()
    }

}

suspend fun transformOpertaotr(){
    (1..10).asFlow()
        .transform {
            emit("emitting strin valuew $it")
            emit(it)

        }
        .collect{
            println(it)
        }
}

suspend fun filterOperator(){
    (1..10).asFlow()
        .filter {
            it % 2 == 0

        }
        .collect{
            println(it)
        }
}


suspend fun mapOperator(){
    (1..10).asFlow()
        .map {
            delay(500L)
            "mapping $it"
        }
        .collect{
            println(it)
        }
}