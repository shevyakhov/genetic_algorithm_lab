package com.example.genetic_algorithm_lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    /** todo
    *
    *  количество точек по горизонтали n - editText
     *  левая граница х1 -editText
     *  верхняя граница y1 - editText
    * нижняя граница - y2 -editText
    *
    * выбор кодирования - вещественное или целочисленное chip
     *
     * количество итераций -editText
     * размер популяции  -editText
    *
    *
     * Мутации - seekbar + textview
     * порог отсечения - seekbar + textview
     * вероятность скрещивания - seekbar  + textview
     *
     *
     * везде кроме выбора нужны  textview
     *
     * результат - canvas
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}