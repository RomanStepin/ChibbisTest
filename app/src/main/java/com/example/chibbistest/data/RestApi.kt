package com.example.chibbistest.data

import com.example.chibbistest.models.Hit
import com.example.chibbistest.models.Restaurant
import com.example.chibbistest.models.Review
import retrofit2.http.GET

interface RestApi {
    @GET("/api/v1/restaurants")
    suspend fun getRestaurants(): List<Restaurant>

    @GET("/api/v1/reviews")
    suspend fun getReviews(): List<Review>

    @GET("/api/v1/hits")
    suspend fun getHits(): List<Hit>
}