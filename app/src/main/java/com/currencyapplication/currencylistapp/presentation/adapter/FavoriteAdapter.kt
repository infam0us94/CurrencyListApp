package com.currencyapplication.currencylistapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.currencyapplication.currencylistapp.R
import com.currencyapplication.currencylistapp.databinding.CurrencyItemBinding
import com.currencyapplication.currencylistapp.domain.model.Rate
import com.currencyapplication.currencylistapp.utils.DiffUtilCallback

class FavoriteAdapter(
    private var listener: OnItemClickListener
) : ListAdapter<Rate, FavoriteAdapter.ItemHolder>(DiffUtilCallback()) {

    inner class ItemHolder(
        private val binding: CurrencyItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: Rate) = with(binding) {
            value.text = rate.rate.toString()
            currency.text = rate.currency
            favoriteButton.setImageResource(R.drawable.ic_baseline_star_24)
            favoriteButton.setOnClickListener {
                listener.removeFromFavorite(rate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            CurrencyItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickListener {
        fun removeFromFavorite(rate: Rate)
    }
}