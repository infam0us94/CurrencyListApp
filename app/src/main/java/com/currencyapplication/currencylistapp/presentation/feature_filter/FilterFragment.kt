package com.currencyapplication.currencylistapp.presentation.feature_filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.currencyapplication.currencylistapp.R
import com.currencyapplication.currencylistapp.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private var currencyOrder: Int? = null
    private var rateOrder: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.alphabetRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.alphabet_ascending -> currencyOrder = 1
                R.id.alphabet_descending -> currencyOrder = 2
            }
        }

        binding.valueRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.value_ascending -> rateOrder = 1
                R.id.value_descending -> rateOrder = 2
            }
        }

        binding.actionButton.setOnClickListener {
            setFragmentResult(
                FILTER_CURRENCY_ORDER, bundleOf(
                    CURRENCY_ORDER to currencyOrder,
                    RATE_ORDER to rateOrder
                )
            )
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FILTER_CURRENCY_ORDER = "FILTER_CURRENCY_ORDER"
        const val CURRENCY_ORDER = "CURRENCY_ORDER"
        const val RATE_ORDER = "RATE_ORDER"
    }
}