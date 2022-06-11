package com.sunah.secretdiary

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    private val diaryEdittext: EditText by lazy {
        findViewById<EditText>(R.id.diaryEdittext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)
        diaryEdittext.setText(detailPreferences.getString("detail",""))

        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                putString("detail", diaryEdittext.text.toString())
            }
        }

        diaryEdittext.addTextChangedListener {

            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }
    }
}