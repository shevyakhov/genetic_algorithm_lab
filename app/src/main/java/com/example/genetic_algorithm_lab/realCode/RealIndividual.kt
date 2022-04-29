package com.example.genetic_algorithm_lab.realCode

import kotlin.random.Random


class RealIndividual //Класс "Особь", содержащий массив генов
{
    var x: Double = (Random.nextDouble() + Random.nextInt(-1000, 1000))
    var fitness: Double = 0.0

}

