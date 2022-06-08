package com.example.chibbistest.ai.hits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chibbistest.R
import com.example.chibbistest.databinding.HitItemLayoutBinding
import com.example.chibbistest.models.Hit

class HitsAdapter : RecyclerView.Adapter<HitsViewHolder>() {
    var items: List<Hit> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitsViewHolder {
        val binding =
            HitItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HitsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HitsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class HitsViewHolder(private val binding: HitItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(hit: Hit) {
        binding.apply {
            hitName.text = hit.ProductName
            Glide
                .with(root.context)
                .load(hit.ProductImage)
                .placeholder(R.drawable.ic_hit_placeholder)
                .into(hitImage)
        }
    }
}