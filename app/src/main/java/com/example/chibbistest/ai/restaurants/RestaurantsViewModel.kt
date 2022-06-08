package com.example.chibbistest.ai.restaurants

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chibbistest.data.Repository
import com.example.chibbistest.models.Restaurant

class RestaurantsViewModel(private val repository: Repository) : ViewModel() {

    suspend fun getRestaurants(query: String): List<Restaurant> {
        return repository.getRestaurants(query)
    }
}