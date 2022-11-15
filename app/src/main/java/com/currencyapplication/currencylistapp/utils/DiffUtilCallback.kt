package com.currencyapplication.currencylistapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.currencyapplication.currencylistapp.domain.model.Rate

class DiffUtilCallback : DiffUtil.ItemCallback<Rate>() {
    override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem == newItem
    }
}