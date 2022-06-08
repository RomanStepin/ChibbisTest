package com.example.chibbistest.ai

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chibbistest.ai.hits.HitsViewModel
import com.example.chibbistest.ai.restaurants.RestaurantsViewModel
import com.example.chibbistest.ai.reviews.ReviewsViewModel
import com.example.chibbistest.data.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(RestaurantsViewModel::class.java) -> return RestaurantsViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(HitsViewModel::class.java) -> return HitsViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(ReviewsViewModel::class.java) -> return ReviewsViewModel(
                repository
            ) as T
            else -> throw IllegalStateException("incorrect ViewModel implementation")
        }
    }
}