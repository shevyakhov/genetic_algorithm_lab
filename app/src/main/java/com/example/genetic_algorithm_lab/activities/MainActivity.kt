package com.example.genetic_algorithm_lab.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.genetic_algorithm_lab.R
import com.example.genetic_algorithm_lab.databinding.ActivityMainBinding
import com.example.genetic_algorithm_lab.utils.Parameters
import com.google.android.material.chip.Chip


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            try {
                Parameters.numberOfIterations = binding.iterationNumber.text.toString().toInt()
                Parameters.populationSize = binding.populationSize.text.toString().toInt()
                val text = (binding.chipGroup.children
                    .toList()
                    .filter { (it as Chip).isChecked }
                    .joinToString(", ") { (it as Chip).text }) == getString(R.string.intCoding)
                Parameters.coding = text

                intent = Intent(this, DisplayActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }

        binding.mutationSeekbar.max = 100
        binding.cutOffSeekbar.max = 100
        binding.crossBreedingSeekbar.max = 100

        binding.mutationSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Parameters.permutationChance = binding.mutationSeekbar.progress.toDouble()
                binding.mutationText.text = binding.mutationSeekbar.progress.toString() + getString(
                    R.string.percent
                )
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.e("touch", "click")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.cutOffSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Parameters.cutOff = binding.cutOffSeekbar.progress.toDouble()
                binding.cutOffText.text = binding.cutOffSeekbar.progress.toString() + getString(
                    R.string.percent
                )
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.e("touch", "click")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.crossBreedingSeekbar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Parameters.crossbreedingProbability =
                    binding.crossBreedingSeekbar.progress.toDouble()
                binding.crossBreedingText.text =
                    binding.crossBreedingSeekbar.progress.toString() + getString(
                        R.string.percent
                    )
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.e("touch", "click")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }
}