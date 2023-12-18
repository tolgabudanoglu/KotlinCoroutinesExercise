package com.example.asynchronousflow3

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        zip()
        combine()
    }
}

suspend fun combine() {
    val numbers = (1..5).asFlow().onEach { delay(300L) }
    val values = flowOf("One", "Two", "Three", "Four", "Five")
        .onEach { delay(400L) }
    numbers.combine(values) { a, b ->
        "$a -> $b"
    }
        .collect { println(it) }
}

suspend fun zip() {
    val english = flowOf("One", "Two", "Three")
    val french = flowOf("Un", "Deux", "Troix")
    english.zip(french) { a, b -> "'$a' in French is '$b'" }
        .collect {
            println(it)
        }
}