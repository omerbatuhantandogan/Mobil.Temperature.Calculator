package com.omerbatuhantandogan.hw1

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.omerbatuhantandogan.hw1.thirdactivity.ThirdActivity

class activitysec : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    private lateinit var buttonCalculateAgain: Button
    private lateinit var buttonGetMoreInfo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_sec)

        textViewResult = findViewById(R.id.textViewResult)
        buttonCalculateAgain = findViewById(R.id.buttonCalculateAgain)
        buttonGetMoreInfo = findViewById(R.id.buttonGetMoreInfo)

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)
        val kelvinTemperature = intent.getDoubleExtra("kelvinTemperature", 0.0)

        val resultText = "Name: $name\nAge: $age\nTemperature in Kelvin: $kelvinTemperature"
        textViewResult.text = resultText

        buttonCalculateAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonGetMoreInfo.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        val kelvinTemperature = intent.getDoubleExtra("kelvinTemperature", 0.0)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog)

        val yesButton = dialog.findViewById<Button>(R.id.buttonYes)
        val noButton = dialog.findViewById<Button>(R.id.buttonNo)

        yesButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("temperatureInfo", getTemperatureMessage(kelvinTemperature))
            startActivity(intent)
            dialog.dismiss()
        }

        noButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun getTemperatureMessage(kelvinTemperature: Double): String {
        return when {
            kelvinTemperature > 325 -> "Very hot temperature"
            kelvinTemperature in 275.0..325.0 -> "Normal temperature"
            kelvinTemperature < 275 -> "Cold temperature"
            else -> "asdadasd"
        }
    }
}
