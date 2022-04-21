package com.example.genetic_algorithm_lab.realCode

class RealPopulation(private val size: Int) {

    private val array: ArrayList<RealIndividual> = ArrayList(size)

    init {
        for (i in 0..size) {
            array.add(RealIndividual())
        }
    }

    operator fun get(n: Int): RealIndividual {
        return array[n]
    }

    operator fun set(i: Int, value: RealIndividual) {
        array[i] = value
    }

    fun getSize(): Int {
        return size
    }
    fun sort(){
        array.sortBy {
            it.fitness
        }
    }
}
