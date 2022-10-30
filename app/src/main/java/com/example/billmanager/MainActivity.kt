package com.example.billmanager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.billmanager.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)
        binding.todayDate.text = "Today's Date is $formatted" // No I18
        binding.selectedDate.text = "Selected Date is $formatted" // No I18

    }

    override fun onStart() {
        super.onStart()

        binding.calendar.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                binding.selectedDate.text = "Selected Date is $date" // No I18
            }
        )

        binding.quantityInput.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus)
            {
                Snackbar.make(v, "Given Quantity is ${(v as EditText).text} litres", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}