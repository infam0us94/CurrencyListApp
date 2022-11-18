package com.currencyapplication.currencylistapp.presentation.feature_currency_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyapplication.currencylistapp.domain.model.Currency
import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.domain.use_case.CurrencyUseCases
import com.currencyapplication.currencylistapp.utils.DispatcherProvider
import com.currencyapplication.currencylistapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyUseCases: CurrencyUseCases,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _currencyList = MutableStateFlow<Resource<Currency>>(Resource.Loading)
    val currencyList: StateFlow<Resource<Currency>> = _currencyList.asStateFlow()

    fun getCurrency(base: String) {
        viewModelScope.launch(dispatchers.io) {
            val currencyList = currencyUseCases.getCurrencyList(base)
            _currencyList.value = Resource.Loading
            when (currencyList) {
                is Resource.Success -> _currencyList.value = currencyList
                is Resource.Failure -> _currencyList.value = Resource.Failure(false, null, null)
                is Resource.Loading -> _currencyList.value = Resource.Loading
            }
        }
    }

    fun addRateInDatabase(rate: Rate) {
        viewModelScope.launch(dispatchers.io) {
            currencyUseCases.addRateInDatabase(rate)
        }
    }
}