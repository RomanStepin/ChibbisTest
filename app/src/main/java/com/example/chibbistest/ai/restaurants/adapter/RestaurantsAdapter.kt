package com.example.chibbistest.ai.restaurants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chibbistest.App
import com.example.chibbistest.R
import com.example.chibbistest.databinding.RestaurantItemLayoutBinding
import com.example.chibbistest.models.Restaurant

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsViewHolder>() {
    var items: List<Restaurant> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding =
            RestaurantItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class RestaurantsViewHolder(private val binding: RestaurantItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(restaurant: Restaurant) {
        val positiveReviewsString = "${restaurant.PositiveReviews} %"
        var specializationsString = ""
        restaurant.Specializations.forEachIndexed { index, specialization ->
            specializationsString += (if (index == 0) "" else "/") + specialization.Name
        }
        binding.apply {
            restaurantName.text = restaurant.Name
            restaurantPositiveReviews.text = positiveReviewsString
            restaurantSpecializations.text = specializationsString
            Glide
                .with(root.context)
                .load(restaurant.Logo)
                .placeholder(R.drawable.ic_restaurant_placeholder)
                .into(restaurantLogo)
        }

    }
}