package com.example.chibbistest.ai.reviews

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chibbistest.data.Repository
import com.example.chibbistest.models.Review

class ReviewsViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getReviews(): List<Review> {
        return repository.getReviews()
    }
}