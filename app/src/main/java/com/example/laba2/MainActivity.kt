package com.example.laba2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var inputN: EditText
    private lateinit var inputA: EditText
    private lateinit var calculateButton: Button
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputN = findViewById(R.id.inputN)
        inputA = findViewById(R.id.inputA)
        calculateButton = findViewById(R.id.calculateButton)
        result = findViewById(R.id.result)

        calculateButton.setOnClickListener { calculate() }
    }

    private fun calculate() {
        val aString = inputA.text.toString()
        val nString = inputN.text.toString()

        if (aString.isNotEmpty() && nString.isNotEmpty()) {
            val a = aString.toDouble()
            val n = nString.toInt()

            if (a != 0.0) { // Проверка на деление на ноль
                val resultvar = SumResult(a, n)
                result.text = "Результат: $resultvar"
            } else {
                result.text = "Значение a не должно быть равно 0."
            }
        } else {
            result.text = "Введите значения a и n."
        }
    }

    private fun SumResult(a: Double, n: Int): Double {
        var sum = 0.0

        for (i in 0..n) {
            var denominator = a // Начинаем с a
            for (j in 1..i) {
                denominator *= (a + j) // Умножаем на (a + j) для каждого j от 1 до i
            }
            sum += 1 / denominator // Добавляем к сумме
        }

        return sum
    }
}