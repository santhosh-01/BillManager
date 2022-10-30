package com.example.billmanager.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.billmanager.entity.Quantity
import com.example.billmanager.repository.QuantityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class QuantityViewModel @Inject constructor(
    val app: Application,
    val quantityRepository: QuantityRepository
): ViewModel() {

    suspend fun insertQuantity(quantity: Quantity): Long = withContext(Dispatchers.IO) {
        quantityRepository.insertQuantity(quantity)
    }

}