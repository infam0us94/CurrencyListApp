package com.currencyapplication.currencylistapp.presentation.feature_currency_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    lateinit var adapter: CurrencyAdapter

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

        lifecycleScope.launchWhenStarted {
            viewModel.currencyList.collect { event ->
                when (event) {
                    is Resource.Success -> {
                        binding.loadStateProgress.visibilityIf(false)
                        // adapter.submitList()
                    }

                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
                        binding.loadStateProgress.visibilityIf(true)
                    }
                    is Resource.Loading -> binding.loadStateProgress.visibilityIf(true)
                }
            }
        }
    }

    private fun initRecView() = with(binding) {
        adapter = CurrencyAdapter()
        recView.layoutManager = LinearLayoutManager(requireContext())
        recView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}