package com.currencyapplication.currencylistapp.presentation.feature_currency_list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.currencyapplication.currencylistapp.R
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

        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.filterFragment)
        }

        initRecView()

        initSpinner()
    }

    private fun getCurrency() {
        viewModel.getCurrency(baseCurrency)
        lifecycleScope.launchWhenStarted {
            viewModel.currencyList.collect { event ->
                when (event) {
                    is Resource.Success -> {
                        viewVisibility(false)
                        adapter.submitList(event.value.rates)
                    }
                    is Resource.Failure -> {
                        viewVisibility(false)
                        showErrorDialog()
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

    private fun showErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_dialog_title)
            .setMessage(R.string.error_dialog_message)
            .setIcon(R.drawable.ic_baseline_error_24)
            .setPositiveButton(R.string.error_dialog_positive_button) { _, _ ->
                getCurrency()
            }.show()
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