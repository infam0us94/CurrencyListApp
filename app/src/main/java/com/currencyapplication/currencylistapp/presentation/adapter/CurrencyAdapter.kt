package com.currencyapplication.currencylistapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.currencyapplication.currencylistapp.data.local.dto.RatesDto
import com.currencyapplication.currencylistapp.databinding.CurrencyItemBinding

class CurrencyAdapter : ListAdapter<RatesDto, CurrencyAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(private val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ratesDto: RatesDto) = with(binding) {
            value.text = ratesDto.AED.toString()
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

    class ItemComparator : DiffUtil.ItemCallback<RatesDto>() {
        override fun areItemsTheSame(oldItem: RatesDto, newItem: RatesDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RatesDto, newItem: RatesDto): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}