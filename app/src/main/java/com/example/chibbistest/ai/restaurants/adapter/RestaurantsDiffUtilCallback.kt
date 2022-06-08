package com.example.chibbistest.ai.restaurants.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.chibbistest.models.Restaurant

open class RestaurantsDiffUtilCallback(
    private val oldList: List<Restaurant>,
    private val newList: List<Restaurant>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].Name == newList[newItemPosition].Name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}