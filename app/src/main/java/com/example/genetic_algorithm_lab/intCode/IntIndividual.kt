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
        Log.e("xxxxx", x.toString())
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
        Log.e("_",xyu)
        decode()
        Log.e("fit",fitness.toString())
    }
}
