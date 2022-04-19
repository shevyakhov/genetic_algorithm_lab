package com.example.genetic_algorithm_lab

import kotlin.random.Random


class Individual //Класс "Особь", содержащий массив генов
{
    var x: Double = (Random.nextDouble() % (1000 - (-1000) + 1) + (-1000)) / 100
    var fitness:Double = 0.0

}

