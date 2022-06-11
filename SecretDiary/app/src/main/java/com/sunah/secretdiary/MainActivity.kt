package com.sunah.secretdiary

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit
import com.sunah.secretdiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val number1 = findViewById<NumberPicker>(R.id.numberPicker1)
        .apply {
            minValue = 0
            maxValue = 9
        }

    private val number2 = findViewById<NumberPicker>(R.id.numberPicker2)
        .apply {
            minValue = 0
            maxValue = 9
        }

    private val number3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }

    private val changeButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changeButton)
    }

    private var changePasswordMode = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1
        number2
        number3

        //저장 되있는 비번과 현재 넘버피커와 비교
        openButton.setOnClickListener {

            if (changePasswordMode) { //true일 경우
                Toast.makeText(this,"비밀번호 변경 중", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //얘가 원래 저장 되어있는 비번
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUser = "${number1.value}${number2.value}${number3.value}"

            if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                startActivity(Intent(this, DiaryActivity::class.java))
            } else {
                showErrorAlertDialog()
            }
        }

        //비밀번호 바꾸기
        changeButton.setOnClickListener {
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${number1.value}${number2.value}${number3.value}"

            if (changePasswordMode) {
                //현재 비번 저장


                passwordPreferences.edit(true){
                    putString("password", passwordFromUser)
                }

                changePasswordMode = false
                changeButton.setBackgroundColor(Color.BLACK)

            } else {


                if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    Toast.makeText(this,"변경할 비번 입력하세요", Toast.LENGTH_SHORT).show()

                    changeButton.setBackgroundColor(Color.RED)

                } else {
                    showErrorAlertDialog()
                }

            }
        }
    }
    private fun showErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("실패")
            .setMessage("비밀번호 틀림")
            .setPositiveButton("확인") { dialog, which -> }
            .create()
            .show()
    }
}


