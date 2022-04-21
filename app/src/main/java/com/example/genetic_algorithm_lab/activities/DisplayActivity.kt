package com.example.genetic_algorithm_lab.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.genetic_algorithm_lab.R
import com.example.genetic_algorithm_lab.databinding.ActivityDisplayBinding
import com.example.genetic_algorithm_lab.intCode.IntGeneticAlgorithm
import com.example.genetic_algorithm_lab.realCode.RealGeneticAlgorithm
import com.example.genetic_algorithm_lab.utils.Parameters.coding
import com.example.genetic_algorithm_lab.utils.Parameters.minPoints
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlin.math.sqrt


class DisplayActivity : AppCompatActivity(),
    OnChartValueSelectedListener {
    private lateinit var binding: ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        minPoints.clear()
        if (coding) {
            val intCode = IntGeneticAlgorithm()
            intCode.genAlgorithm()
        } else {
            val realCode = RealGeneticAlgorithm()
            realCode.genAlgorithm()
        }


        val lineEntry = ArrayList<Entry>()
        Log.e("e", minPoints.toString())
        minPoints.sortDescending()
        Toast.makeText(this, "${minPoints.size}", Toast.LENGTH_SHORT).show()
        for (i in minPoints.size - 2 downTo 0) {
            lineEntry.add(
                Entry(
                    func(minPoints[i].toFloat()),
                    minPoints[i].toFloat()
                )
            )
        }


        val lineDataset = LineDataSet(lineEntry, whatVal())
        lineDataset.color = resources.getColor(R.color.teal_200)
        val data = LineData(lineDataset)
        binding.chart.description.text = whatCode()
        binding.chart.setBorderColor(getColor(R.color.broken))
        binding.chart.fitScreen()
        binding.chart.data = data
        binding.chart.setBackgroundColor(resources.getColor(R.color.hearts))
        binding.chart.animateXY(3000, 3000)


    }

    private fun whatVal(): String {
        return if (coding) {
            getString(R.string.intVal)
        } else
            getString(R.string.floatVal)
    }

    private fun whatCode(): String {
        return if (coding) {
            getString(R.string.intCoding)
        } else
            getString(R.string.floatCoding)
    }

    private fun func(y: Float): Float {
        Log.e("y", y.toString())
        return sqrt(y - 4)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        Log.e("","")
    }

    override fun onNothingSelected() {
        Log.e("","")
    }
}