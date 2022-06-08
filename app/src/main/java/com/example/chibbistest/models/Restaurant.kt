package com.example.chibbistest.models

data class Restaurant(
    val DeliveryCost: Int,
    val DeliveryTime: Int,
    val Logo: String,
    val MinCost: Int,
    val Name: String,
    val PositiveReviews: Int,
    val ReviewsCount: Int,
    val Specializations: List<Specialization>
)

data class Specialization(
    val Name: String
)