package com.example.genetic_algorithm_lab.intCode

import android.util.Log
import com.example.genetic_algorithm_lab.utils.BIT_LENGTH
import com.example.genetic_algorithm_lab.utils.LIMIT
import com.example.genetic_algorithm_lab.utils.Parameters
import com.example.genetic_algorithm_lab.utils.Parameters.crossbreedingProbability
import com.example.genetic_algorithm_lab.utils.Parameters.cutOff
import com.example.genetic_algorithm_lab.utils.Parameters.numberOfIterations
import com.example.genetic_algorithm_lab.utils.Parameters.permutationChance
import com.example.genetic_algorithm_lab.utils.Parameters.populationSize
import kotlin.math.abs
import kotlin.random.Random

class IntGeneticAlgorithm {
    private val size: Int = populationSize - 1 //начальный размер популяции
    private var sizeN = size //размер популяции, который будет изменяться во время работы программы
    private val population: IntPopulation = IntPopulation(size) //формирование популяции
    private val minPointsLocal = ArrayList<Double>()
    private val sumPointsLocal = ArrayList<Double>()

    fun genAlgorithm(): IntIndividual {
        for (i in 0 until numberOfIterations)  // Количество итераций
        {
            assessment() //Метод, выполняющий рассчет приспособленности и сортировку по значению оценочной функции
            truncateSelection() //Метод, благодаря которому осуществляется отбор особей
            crossBreeding() //Метод, который реализует скрещивание особей
            mutation() //Метод, отвечающий за мутацию
            addPoint() // для графика
            if (abs(population[0].fitness - population[sizeN - 1].fitness) <= 0.001) {
                break // проверка на вырожденную популяцию
            }
        }
        Parameters.minPoints = minPointsLocal
        Parameters.sumPoints = sumPointsLocal
        return population[0]
    }

    private fun func(x: Double): Double // возвращает значение функции от заданного аргумента
    {
        return x * x + 4
    }

    private fun assessment() {
        for (i in 0 until sizeN) {
            population[i].fitness =
                func(population[i].x) // рассчет оценочной функции(приспобленности)
        }
        sort()
    }

    private fun addPoint() // нахождение минимального значения и среднего значения функции по итерациям
    {
        var sum = 0.0
        var min = 99999.0
        for (i in 0 until sizeN) {
            sum += func(population[i].x)
            if (min > func(population[i].x)) {
                min = func(population[i].x)
            }
        }
        sum /= sizeN

        sumPointsLocal.add(sum)
        minPointsLocal.add(min)
    }

    private fun truncateSelection() {
        val l: Double = cutOff / 100 //порог отсечения
        var i = size - 1
        while (i > l * size) {
            for (j in 0 until BIT_LENGTH) {
                population[i].bits[j] = false
            }
            population[i].decode()
            sizeN--
            i--
        }
    }

    private fun crossBreeding() // Скрещивание особей с помощью 1 - точечного кроссинговера
    {
        val randomDivider: Int =
            Random.nextInt(0, 100) % (BIT_LENGTH - 2) + 1 // случайная точка разрыва
        val p: Double = crossbreedingProbability / 100 //Вероятность скрещивания
        val size = sizeN //Для формирования потомков
        while (sizeN < this.size) //Восстанавливаем количество потомков
        {
            val i = (Random.nextInt(0, LIMIT) % size)
            val j = (Random.nextInt(0, LIMIT) % size)
            if (p > Random.nextInt(0, LIMIT) % 100 * 0.01) // вероятность скрещивания
            {
                for (r in 0 until randomDivider)  // гены до 1 точки разрыва
                {
                    population[sizeN].bits[r] = population[i].bits[r]
                }
                for (t in randomDivider until BIT_LENGTH)  // гены после 1 точки разрыва
                {
                    population[sizeN].bits[t] = population[j].bits[t]
                }
                Log.e("crossBreeding", "crossBreeding")
                population[sizeN].decode()
                Log.e("crossBreeding", "End CB")
                sizeN++
            }
        }
    }

    private fun mutation() // битовая мутация
    {
        val mutation: Double = permutationChance / 100 //вероятность мутации
        for (i in 0 until sizeN) {
            for (j in 0 until BIT_LENGTH) {
                if (mutation > (Random.nextInt(0, LIMIT) % size) % 100 * 0.01) {
                    population[i].bits[j] = !population[i].bits[j]
                }
            }
            Log.e("mutation", "mutation")
            population[i].decode()
            Log.e("mutation", "END")

        }
    }

    private fun sort() {
        var h = 1
        while (h <= sizeN / 9) {
            h = h * 3 + 1
        }

        while (h >= 1) {
            for (i in h until sizeN) {
                var j = i - h
                while (j >= 0 && population[j].fitness > population[j + h].fitness) {
                    val temp: IntIndividual = population[j]
                    population[j] = population[j + h]
                    population[j + h] = temp
                    j -= h
                }
            }
            h = (h - 1) / 3
        }
    }
}
