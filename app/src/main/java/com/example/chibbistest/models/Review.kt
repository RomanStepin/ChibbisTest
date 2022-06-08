package com.example.chibbistest.models

data class Review(
    val DateAdded: String,
    val IsPositive: Boolean,
    val Message: String,
    val RestaurantName: String,
    val UserFIO: String
)