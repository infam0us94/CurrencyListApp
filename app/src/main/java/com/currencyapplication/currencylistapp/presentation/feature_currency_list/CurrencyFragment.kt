package com.currencyapplication.currencylistapp.presentation.feature_currency_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.currencyapplication.currencylistapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment(R.layout.fragment_currency) {

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrency()
    }

}