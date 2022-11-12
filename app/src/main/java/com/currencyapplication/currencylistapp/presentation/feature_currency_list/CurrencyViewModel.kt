package com.currencyapplication.currencylistapp.presentation.feature_currency_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyapplication.currencylistapp.data.local.dto.CurrencyDto
import com.currencyapplication.currencylistapp.domain.use_case.GetCurrencyList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val useCase: GetCurrencyList
) : ViewModel() {

    private val _currencyList = MutableLiveData<CurrencyDto>()
    val currencyList: LiveData<CurrencyDto> = _currencyList

    fun getCurrency() {
        viewModelScope.launch {
            _currencyList.value = useCase.getCurrencyList()
        }
    }
}