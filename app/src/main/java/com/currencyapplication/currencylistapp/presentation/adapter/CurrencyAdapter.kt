package com.currencyapplication.currencylistapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.currencyapplication.currencylistapp.R
import com.currencyapplication.currencylistapp.databinding.CurrencyItemBinding
import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.utils.DiffUtilCallback

class CurrencyAdapter(
    private val onClick: (Rate) -> Unit
) : ListAdapter<Rate, CurrencyAdapter.ItemHolder>(DiffUtilCallback()) {

    inner class ItemHolder(
        private val binding: CurrencyItemBinding,
        private val onClick: (Rate) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: Rate) = with(binding) {
            value.text = rate.rate.toString()
            currency.text = rate.currency
            favoriteButton.setImageResource(R.drawable.ic_baseline_star_border_24)
            favoriteButton.setOnClickListener {
                onClick(rate)
                Toast.makeText(itemView.context, "${rate.currency} добавлен в избранное", Toast.LENGTH_SHORT)
                    .show()
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