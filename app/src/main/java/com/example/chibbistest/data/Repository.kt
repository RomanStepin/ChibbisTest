package com.example.chibbistest.data

import android.util.Log
import com.example.chibbistest.models.Hit
import com.example.chibbistest.models.Restaurant
import com.example.chibbistest.models.Review
import retrofit2.http.GET
import javax.inject.Inject

class Repository @Inject constructor(private val restApi: RestApi) {

    suspend fun getRestaurants(query: String): List<Restaurant> {
        return try {
            restApi.getRestaurants().filter {
                it.Name.uppercase().contains(query.uppercase())
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun getReviews(): List<Review> {
        return try {
            restApi.getReviews()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun getHits(): List<Hit> {
        return try {
            restApi.getHits()
        } catch (e: Exception) {
            listOf()
        }
    }
}