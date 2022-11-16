package com.currencyapplication.currencylistapp.presentation.feature_favorite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.currencyapplication.currencylistapp.databinding.FragmentFavoriteBinding
import com.currencyapplication.currencylistapp.presentation.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var adapter: FavoriteAdapter
    private lateinit var baseCurrency: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecView()

        initSpinner()
    }

    private fun initRecView() = with(binding) {
        adapter = FavoriteAdapter { rate -> viewModel.removeRateFromDatabase(rate) }
        recView.layoutManager = LinearLayoutManager(requireContext())
        recView.adapter = adapter
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

    private fun getCurrency() {
        viewModel.getCurrency(baseCurrency)
        lifecycleScope.launchWhenStarted {
            viewModel.favoritesList.collect { event ->
                adapter.submitList(event)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}