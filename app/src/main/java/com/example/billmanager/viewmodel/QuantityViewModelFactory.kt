//package com.example.billmanager.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.billmanager.BillManagerApp
//import com.example.billmanager.repository.QuantityRepository
//
//class QuantityViewModelFactory(
//    val app: BillManagerApp,
//    val quantityRepository: QuantityRepository
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(QuantityViewModel::class.java)) {
//            return QuantityViewModel(app, quantityRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel Class")
//    }
//
//}