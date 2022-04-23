package com.example.genetic_algorithm_lab.intCode

import android.util.Log
import com.example.genetic_algorithm_lab.utils.BIT_LENGTH
import kotlin.random.Random

class IntIndividual {
    var x = 0.0 // X - Хромосома, fitness- значение приспобленности
    var fitness = 0.0
    var bits: BooleanArray
    private var size = BIT_LENGTH //разрядность

    fun decode() //метод декодирования,
    {
        var str = ""
        for (i in bits) {
            str += if (i) {
                "1"
            } else
                "0"
        }
        val int = str.toInt(2)
        x = int.toDouble()
        Log.e("new", x.toString())
        fitness = x * x + 4
    }

    init {
        bits = BooleanArray(size)
        for (i in 0 until size) {
            bits[i] = Random.nextBoolean() // случайная генерациия гена
        }
        var temp = ""
        for (i in bits) {
            temp += if (i) {
                "1"
            } else
                "0"

        }
        Log.e("_", temp)
        decode()
        Log.e("fit", fitness.toString())
    }
}
