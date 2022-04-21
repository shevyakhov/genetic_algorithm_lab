package com.example.genetic_algorithm_lab.intCode

import android.util.Log
import kotlin.random.Random

class IntIndividual {
    private val xMin = -10
    private val xMax = 10
    var x = 0.0 // X - Хромосома, fitness- значение приспобленности
    var fitness = 0.0
    var bits: BooleanArray
    private var size = 10 //разрядность


    fun decode() //метод декодирования,
    { //для перевода закодированного двоичного значения гена в дробное;
        var value = 0.0
        var razryad = 1.0
        for (j in size - 1 downTo 0) {
            if (bits[j]) {
                value += razryad
            }
            razryad *= 2
        }
        x = xMin + (xMax - xMin) * value / (razryad - 1)
        fitness = x * x + 4
    }
    fun decodea() //метод декодирования,
    {
        var str = ""
        for (i in bits){
            str += if (i){
                "1"
            }else
                "0"
        }
        val int = str.toInt(2)
        x = int.toDouble()
        fitness = x * x + 4
    }

    init {
        bits = BooleanArray(size)
        for (i in 0 until size) {
            bits[i] = Random.nextBoolean() // случайная генерациия гена
        }
        var xyu = ""
        for (i in bits){
            if (i){
                xyu+="1"
            }else
                xyu+="0"

        }
        Log.e("x",xyu)
    }
}
