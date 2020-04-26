package com.psob96.nativeapp

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun testCodePerformance(rows: Int): List<Long> {

    val text = generateText(rows)
    val personList: MutableList<Person> = mutableListOf()

    val textToListTIme = measureTimeMillis {
        val lines = text.split("\n")
        lines.forEach { line ->
            val props = line.split(",")
            val name = props[0]
            val surname =  props[1]
            personList.add(Person(name, surname))
        }
    }

    val sortTime = measureTimeMillis {
        personList.sortWith(compareBy({ it.surname }, { it.name }))
    }

    return listOf(textToListTIme, sortTime)
}

fun testEmptyMethod() = measureNanoTime { } / 1000000f

