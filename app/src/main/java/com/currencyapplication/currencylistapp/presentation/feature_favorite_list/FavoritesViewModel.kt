package com.currencyapplication.currencylistapp.presentation.feature_favorite_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.domain.use_case.CurrencyUseCases
import com.currencyapplication.currencylistapp.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val currencyUseCases: CurrencyUseCases,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _favoritesList = MutableStateFlow<List<Rate>>(listOf())
    val favoritesList: StateFlow<List<Rate>> = _favoritesList.asStateFlow()

    fun getCurrency(base: String, currency: Int?, rate: Int?) {
        viewModelScope.launch(dispatchers.io) {
            val rates = currencyUseCases.getRatesFromDatabase(base, currency, rate)
            _favoritesList.value = rates
        }
    }

    fun removeRateFromDatabase(rate: Rate) {
        viewModelScope.launch {
            currencyUseCases.removeRateFromDatabase(rate)
        }
    }
}