package com.example.genetic_algorithm_lab.intCode

import kotlin.random.Random

class IntIndividual {
    private val xMin = -10
    private val xMax = 10
    var x = 0.0 // X - Хромосома, fitness- значение приспобленности
    var fitness = 0.0
    var string: BooleanArray
    var size = 10 //разрядность


    fun decode() //метод декодирования,
    { //для перевода закодированного двоичного значения гена в дробное;
        var value = 0.0
        var razryad = 1.0
        for (j in size - 1 downTo 0) {
            if (string[j]) {
                value += razryad
            }
            razryad *= 2
        }
        x = xMin + (xMax - xMin) * value / (razryad - 1)
        fitness = x * x + 4
    }

    init {
        string = BooleanArray(size)
        for (i in 0 until size) {
            string[i] = Random.nextBoolean() // случайная генерациия гена
        }
    }
}
