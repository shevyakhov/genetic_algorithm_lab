package com.example.genetic_algorithm_lab

class Population(private val size: Int) {
    private val array: ArrayList<Individual> = ArrayList(size)
    operator fun get(n: Int): Individual {
        return array[n]
    }

    operator fun set(i: Int, value: Individual) {
        array[i] = value
    }
}
