package com.example.chibbistest.ai.hits.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.chibbistest.models.Hit

open class HitsDiffUtilCallback(private val oldList: List<Hit>, private val newList: List<Hit>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].ProductName == newList[newItemPosition].ProductName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}