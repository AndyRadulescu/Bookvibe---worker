package org.example.worker.dto

data class BookVibeVolumeDto (
    val id: String,
    val title: String,
    val subtitle: String?,
    val authors: List<String>,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val industryIdentifiers: List<IndustryIdentifiersDto>?,
    val pageCount: Number,
    val categories: List<String>?,
    val averageRating: Number?,
    val ratingCount: Number?,
    val maturityRating: String,
    val imageLink: String,
    val language: String?
)
