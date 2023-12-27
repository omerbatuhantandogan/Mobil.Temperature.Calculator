package com.omerbatuhantandogan.hw1.thirdactivity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.omerbatuhantandogan.hw1.MainActivity
import com.omerbatuhantandogan.hw1.R

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_three)

        val textViewInfo = findViewById<TextView>(R.id.textViewInfo)
        val buttonCalculateAgain = findViewById<Button>(R.id.buttonCalculateAgain)

        val temperatureInfo = intent.getStringExtra("temperatureInfo")
        textViewInfo.text = temperatureInfo

        buttonCalculateAgain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}