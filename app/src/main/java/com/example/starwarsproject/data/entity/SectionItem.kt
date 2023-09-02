package com.example.starwarsproject.data.entity

data class SectionItem<T>(
    val headline: String,
    val items: List<T>
)
