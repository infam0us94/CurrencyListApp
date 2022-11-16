package com.currencyapplication.currencylistapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.currencyapplication.currencylistapp.databinding.CurrencyItemBinding
import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.utils.DiffUtilCallback

class FavoriteAdapter(
    private val onClick: (Rate) -> Unit
) : ListAdapter<Rate, FavoriteAdapter.ItemHolder>(DiffUtilCallback()) {

    class ItemHolder(
        private val binding: CurrencyItemBinding,
        private val onClick: (Rate) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: Rate) = with(binding) {
            value.text = rate.rate.toString()
            currency.text = rate.currency
            favoriteButtonAdd.setOnClickListener {
                onClick(rate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            CurrencyItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}