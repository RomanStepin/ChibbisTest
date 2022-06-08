package com.example.chibbistest.ai.reviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chibbistest.R
import com.example.chibbistest.databinding.ReviewItemLayoutBinding
import com.example.chibbistest.models.Review
import com.example.chibbistest.parseDateToSimpleFormat

class ReviewsAdapter : RecyclerView.Adapter<ReviewsViewHolder>() {
    var items: List<Review> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val binding =
            ReviewItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ReviewsViewHolder(private val binding: ReviewItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(review: Review) {
        binding.apply {
            val reviewTitleString =
                "${review.UserFIO} ${binding.root.context.getString(R.string.about_restaurant)} ${review.RestaurantName}"
            reviewTitle.text = reviewTitleString
            reviewText.text = review.Message
            reviewDate.text = parseDateToSimpleFormat(review.DateAdded)
            Glide
                .with(root.context)
                .load(if (review.IsPositive) R.drawable.ic_like else R.drawable.ic_dont_like)
                .into(reviewIsPositiveIcon)
        }
    }
}