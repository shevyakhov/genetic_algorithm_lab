package com.example.genetic_algorithm_lab.intCode

//Класс "Популяция",содержащий массив объектов класса "Особь"
class IntPopulation(private val size: Int) {
    private val array: ArrayList<IntIndividual> = ArrayList(size)

    init {
        for (i in 0..size) {
            array.add(IntIndividual())
        }
    }

    operator fun get(n: Int): IntIndividual {
        return array[n]
    }

    operator fun set(i: Int, value: IntIndividual) {
        array[i] = value
    }

    fun getSize(): Int {
        return size
    }

}

