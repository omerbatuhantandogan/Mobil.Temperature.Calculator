// MainActivity.kt
package com.omerbatuhantandogan.hw1

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var seekBarAge: SeekBar
    private lateinit var spinnerUnit: Spinner
    private lateinit var editTextTemperature: EditText
    private lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        seekBarAge = findViewById(R.id.seekBarAge)
        spinnerUnit = findViewById(R.id.spinnerUnit)
        editTextTemperature = findViewById(R.id.editTextTemperature)
        buttonCalculate = findViewById(R.id.buttonCalculate)

        seekBarAge.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                findViewById<TextView>(R.id.textViewAge).text = "Age: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })

        val spinnerItems = createSpinnerItems()
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUnit.adapter = spinnerAdapter

        buttonCalculate.setOnClickListener {
            calculateTemperature()
        }
    }

    private fun calculateTemperature() {
        val name = editTextName.text.toString()
        val age = seekBarAge.progress
        val unit = spinnerUnit.selectedItem.toString()
        val temperatureInput = editTextTemperature.text.toString()

        if (name.isEmpty() || temperatureInput.isEmpty()) {
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val temperature = temperatureInput.toDouble()
            val kelvinTemperature = convertToKelvin(temperature, unit)
            showResultDialog(name, age, kelvinTemperature)
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid temperature input", Toast.LENGTH_SHORT).show()
        }
    }

    private fun convertToKelvin(temperature: Double, unit: String): Double {
        return if (unit == "Celsius") {
            temperature + 273.15
        } else {
            (temperature - 32) * 5 / 9 + 273.15
        }
    }

    private fun showResultDialog(name: String, age: Int, kelvinTemperature: Double) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Temperature Calculation Result")
            .setMessage("Name: $name\nAge: $age\nTemperature in Kelvin: $kelvinTemperature")
            .setPositiveButton("OK") { dialog, which ->
                val intent = Intent(this, activitysec::class.java)
                intent.putExtra("name", name)
                intent.putExtra("age", age)
                intent.putExtra("kelvinTemperature", kelvinTemperature)
                startActivity(intent)
            }
        builder.show()
    }

    private fun createSpinnerItems(): List<String> {
        val spinnerItems = ArrayList<String>()
        spinnerItems.add("Celsius")
        spinnerItems.add("Fahrenheit")
        return spinnerItems
    }
}