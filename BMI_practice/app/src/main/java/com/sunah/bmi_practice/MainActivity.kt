package com.sunah.bmi_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultButton = findViewById<Button>(R.id.check_button)

        val height_text:EditText = findViewById(R.id.height_text)
        val weight_text:EditText = findViewById(R.id.weight_text)


        resultButton.setOnClickListener {

            if (height_text.text.isEmpty() || weight_text.text.isEmpty()) {
                Toast.makeText(this,"빈칸이 있음",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //빈 값이 아닐 경우
            val height: Int = height_text.text.toString().toInt()
            val weight: Int = weight_text.text.toString().toInt()

            var intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height",height)
            intent.putExtra("weight",weight)
            startActivity(intent)
        }
    }
}
