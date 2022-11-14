package com.currencyapplication.currencylistapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.currencyapplication.currencylistapp.databinding.CurrencyItemBinding
import com.currencyapplication.currencylistapp.domain.model.RatesEntity

class CurrencyAdapter : ListAdapter<RatesEntity, CurrencyAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(private val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ratesEntity: RatesEntity) = with(binding) {
            value.text = ratesEntity.rate.toString()
            currency.text = ratesEntity.currency
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

    class ItemComparator : DiffUtil.ItemCallback<RatesEntity>() {
        override fun areItemsTheSame(oldItem: RatesEntity, newItem: RatesEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RatesEntity, newItem: RatesEntity): Boolean {
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