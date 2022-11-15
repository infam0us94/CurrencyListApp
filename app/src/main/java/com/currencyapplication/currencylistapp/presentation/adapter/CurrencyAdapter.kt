package com.currencyapplication.currencylistapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.currencyapplication.currencylistapp.databinding.CurrencyItemBinding
import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.utils.DiffUtilCallback

class CurrencyAdapter : ListAdapter<Rate, CurrencyAdapter.ItemHolder>(DiffUtilCallback()) {

    class ItemHolder(private val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: Rate) = with(binding) {
            value.text = rate.rate.toString()
            currency.text = rate.currency
        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(
                    CurrencyItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}