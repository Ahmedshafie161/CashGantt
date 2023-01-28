package com.example.mycashgantt

import java.time.LocalDate

data class Item(
    val name: String,
    val price: Double,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val costMonths: MutableList<CustomMonth>
)

