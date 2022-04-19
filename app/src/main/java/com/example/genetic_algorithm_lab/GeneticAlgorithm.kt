package com.example.genetic_algorithm_lab

import com.example.genetic_algorithm_lab.Parameters.cutOff
import com.example.genetic_algorithm_lab.Parameters.numberOfIterations
import com.example.genetic_algorithm_lab.Parameters.permutationChance
import com.example.genetic_algorithm_lab.Parameters.populationSize
import com.example.genetic_algorithm_lab.Parameters.crossbreedingProbability
import kotlin.math.abs
import kotlin.random.Random


class GeneticAlgorithm // управляющий класс
{
    private val size: Int = populationSize //начальный размер популяции
    private var sizeN = size //размер популяции, который будет изменяться во время работы программы
    private val population = Population(size) //Формирование популяции
    fun genAlgorithm(): Individual {
        for (i in 0 until numberOfIterations)  // Количество итераций
        {
            assessment() //Метод, выполняющий рассчет приспособленности и сортировку по значению оценочной функции
            truncateSelection() //Метод, благодаря которому осуществляется отбор особей
            crossBreeding() //Метод, который реализует скрещивание особей
            mutation() //Метод, отвечающий за мутацию
            graphic() // для графика
            if (abs(population[0].fitness - population[sizeN - 1].fitness) <= 0.001) {
                break // проверка на вырожденную популяцию
            }
        }
        return population[0]
    }

    private fun func(x: Double): Double // возвращает значение функции от заданного аргумента
    {
        return x * x + 4 // оценочная функция
    }

    private fun graphic() // нахождение минимального значения и среднего значения функции по итерациям
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
/*
        todo graphic functions

        SrPoints.get(k) = sum
        MinPoints.get(k) = min
        k++
*/
    }

    private fun assessment() {
        var h: Int
        for (i in 0 until sizeN)  // рассчет оценочной функции(приспобленности)
        {
            population[i].fitness = func(population[i].x)
        }
        h = 1
        while (h <= sizeN / 9) {
            //сортировка Шелла (Индекс у более приспобленных меньше)
            h = h * 3 + 1
        }

        while (h >= 1) {
            for (i in h until sizeN) {
                var j = i - h
                while (j >= 0 && population[j].fitness > population[j + h].fitness) {
                    val temp: Individual = population[j]
                    population[j] = population[j + h]
                    population[j + h] = temp
                    j -= h
                }
            }
            h = (h - 1) / 3
        }
    }

    private fun truncateSelection() // селекция усечением
    {
        val l: Double = cutOff / 100 //порог отсечения
        var i = size - 1
        while (i > l * size) {
            /* todo fix
            population[i].x = null
            population[i].fitness = null*/
            sizeN--
            i--
        }
    }

    private fun crossBreeding() // Метод, осуществляющий арифметический кроссинговер
    {
        val size = sizeN
        val p: Double = crossbreedingProbability / 100 //Вероятность скрещивания
        while (sizeN < this.size) //цикл будет до тех пор, пока число особей не вернется к первоначальному количеству
        {
            val i = (Random.nextInt() % size)
            val j = (Random.nextInt() % size)
            val l: Double = Random.nextInt() % 100 * 0.01
            if (p > Random.nextInt() % 100 * 0.01) {
                population[sizeN++].x = l * population[i].x + (1 - l) * population[j].x
            }
        }
        sizeN--
    }

    private fun mutation() {
        val mut: Double = permutationChance / 100 //вероятность мутации
        for (i in 0 until sizeN) {
            if (mut > Random.nextInt() % 100 * 0.01) {
                population[i].x += Random.nextInt() % 50 * 0.01 - Random.nextInt() % 50 * 0.01
            }
        }
    }
}