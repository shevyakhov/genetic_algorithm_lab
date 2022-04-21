package com.example.genetic_algorithm_lab.realCode

import android.util.Log
import com.example.genetic_algorithm_lab.utils.LIMIT
import com.example.genetic_algorithm_lab.utils.Parameters
import com.example.genetic_algorithm_lab.utils.Parameters.crossbreedingProbability
import com.example.genetic_algorithm_lab.utils.Parameters.cutOff
import com.example.genetic_algorithm_lab.utils.Parameters.numberOfIterations
import com.example.genetic_algorithm_lab.utils.Parameters.permutationChance
import com.example.genetic_algorithm_lab.utils.Parameters.populationSize
import kotlin.math.abs
import kotlin.random.Random


class RealGeneticAlgorithm // управляющий класс
{
    private val size: Int = populationSize //начальный размер популяции
    private var sizeN = size //размер популяции, который будет изменяться во время работы программы
    private val population = RealPopulation(size) //Формирование популяции
    private val minPointsLocal = ArrayList<Double>()


    fun genAlgorithm(): RealIndividual {
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
        return population[0]
    }

    private fun func(x: Double): Double // возвращает значение функции от заданного аргумента
    {
        return x * x + 4 // оценочная функция
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

        //SrPoints[k] = sum
        minPointsLocal.add(min)
    }

    private fun assessment() {
        for (i in 0 until sizeN)  // рассчет оценочной функции(приспобленности)
        {
            population[i].fitness = func(population[i].x)
        }
        sort()
    }

    private fun truncateSelection() // селекция усечением
    {
        val l: Double = cutOff / 100 //порог отсечения
        var i = size - 1
        while (i > l * size) {

            population[i].x = 0.0
            population[i].fitness = 0.0
            sizeN--
            i--
        }
    }


    private fun crossBreeding() // Метод, осуществляющий арифметический кроссинговер
    {
        val size = sizeN
        val probability: Double = crossbreedingProbability / 100 //Вероятность скрещивания
        while (sizeN < this.size) //цикл будет до тех пор, пока число особей не вернется к первоначальному количеству
        {
            if (size != 0) {
                val i = (Random.nextInt(LIMIT) % size)
                val j = (Random.nextInt(LIMIT) % size)
                val l: Double = Random.nextInt(LIMIT) % 100 * 0.01
                if (probability > Random.nextInt(LIMIT) % 100 * 0.01) {
                    population[sizeN++].x = l * population[i].x + (1 - l) * population[j].x
                }
            }
        }
        sizeN--
    }

    private fun mutation() {
        val mutationChance: Double = permutationChance / 100 //вероятность мутации
        for (i in 0 until sizeN) {
            if (mutationChance > Random.nextInt(LIMIT) % 100 * 0.01) {
                population[i].x += Random.nextInt(LIMIT) % 50 * 0.01 - Random.nextInt(1000) % 50 * 0.01
            }
        }
    }

    fun check() {
        for (i in 0..size) {
            Log.e("ppp", population[i].x.toString())
        }
    }

    private fun sort() {
        var h = 1
        while (h >= 1) {
            for (i in h until sizeN) {
                var j = i - h
                while (j >= 0 && population[j].fitness > population[j + h].fitness) {
                    val temp: RealIndividual = population[j]
                    population[j] = population[j + h]
                    population[j + h] = temp
                    j -= h
                }
            }
            h = (h - 1) / 3
        }
    }
}