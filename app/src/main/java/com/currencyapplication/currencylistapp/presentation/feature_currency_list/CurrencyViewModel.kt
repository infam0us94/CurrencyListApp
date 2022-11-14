package com.currencyapplication.currencylistapp.presentation.feature_currency_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyapplication.currencylistapp.domain.model.CurrencyEntity
import com.currencyapplication.currencylistapp.domain.use_case.GetCurrencyList
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
    private val useCase: GetCurrencyList,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _currencyList = MutableStateFlow<Resource<CurrencyEntity>>(Resource.Loading)
    val currencyList: StateFlow<Resource<CurrencyEntity>> = _currencyList.asStateFlow()

    fun getCurrency(base: String) {
        viewModelScope.launch(dispatchers.io) {
            val currencyList = useCase.getCurrencyList(base)
            _currencyList.value = Resource.Loading
            when (currencyList) {
                is Resource.Success -> _currencyList.value = currencyList
                is Resource.Failure -> _currencyList.value = Resource.Failure(false, null, null)
                is Resource.Loading -> _currencyList.value = Resource.Loading
            }
        }
    }
}