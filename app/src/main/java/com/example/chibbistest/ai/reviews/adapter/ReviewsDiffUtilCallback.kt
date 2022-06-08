package com.example.chibbistest.ai.reviews.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.chibbistest.models.Review

class ReviewsDiffUtilCallback(
    private val oldList: List<Review>,
    private val newList: List<Review>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].Message == newList[newItemPosition].Message
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}