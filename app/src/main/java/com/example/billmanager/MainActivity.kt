package com.example.billmanager

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.billmanager.databinding.ActivityMainBinding
import com.example.billmanager.entity.Quantity
import com.example.billmanager.viewmodel.QuantityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DateFormatSymbols
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var quantityViewModel: QuantityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        quantityViewModel = ViewModelProvider(this)[QuantityViewModel::class.java]
        setContentView(binding.root)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)
        val currMonth = DateFormatSymbols().months[current.month.value].toString()
        binding.todayDate.text = "Today's Date is $formatted" // No I18
        binding.selectedDate.text = "Selected Date is $formatted" // No I18
        binding.totalQuantityThisMonth.text = "Total Quantity for $currMonth is 0 litre" // No I18

    }

    override fun onStart() {
        super.onStart()

        binding.calendar.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { view, year, month, dayOfMonth ->
                val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                binding.selectedDate.text = "Selected Date is $date" // No I18
            }
        )

        binding.quantityInput.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER){
                hideKeyboard(v)
                v.clearFocus()
                true
            }
            else false
        }

        binding.quantityInput.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus)
            {
                val quantity = Quantity(joined_date = OffsetDateTime.now(), quantity = binding.quantityInput.text.toString().toDouble())
                lifecycleScope.launch {
                    quantityViewModel.insertQuantity(quantity)
                }
            }
        }
    }

    private fun Activity.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}