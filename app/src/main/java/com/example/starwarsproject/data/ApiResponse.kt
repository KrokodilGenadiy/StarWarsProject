package com.example.starwarsproject.data

data class ApiResponse<T>(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<T>
)