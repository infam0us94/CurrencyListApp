package com.currencyapplication.currencylistapp.presentation.feature_currency_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.currencyapplication.currencylistapp.databinding.FragmentCurrencyBinding
import com.currencyapplication.currencylistapp.presentation.adapter.CurrencyAdapter
import com.currencyapplication.currencylistapp.utils.Resource
import com.currencyapplication.currencylistapp.utils.visibilityIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment() {

    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CurrencyViewModel by viewModels()

    private lateinit var adapter: CurrencyAdapter
    private lateinit var baseCurrency: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecView()

        initSpinner()
    }

    private fun getCurrency() {
        lifecycleScope.launchWhenStarted {
            viewModel.currencyList.collect { event ->
                when (event) {
                    is Resource.Success -> {
                        viewVisibility(false)
                        adapter.submitList(event.value.rates)
                    }
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
                        viewVisibility(true)
                    }
                    is Resource.Loading -> viewVisibility(true)
                }
            }
        }
    }

    private fun initSpinner() {
        binding.currencySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapter: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    baseCurrency = adapter?.getItemAtPosition(position).toString()
                    viewModel.getCurrency(baseCurrency)
                    getCurrency()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun initRecView() = with(binding) {
        adapter = CurrencyAdapter { rate -> viewModel.addRateInDatabase(rate) }
        recView.layoutManager = LinearLayoutManager(requireContext())
        recView.adapter = adapter
    }

    private fun viewVisibility(isVisible: Boolean) {
        with(binding) {
            loadStateProgress.visibilityIf(isVisible)
            recView.visibilityIf(!isVisible)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}