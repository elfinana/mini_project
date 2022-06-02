package com.sunah.mini

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RandomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        val place = findViewById<TextView>(R.id.place_text)

        val name = intent.getStringExtra("name")

        place.text = name
    }
}