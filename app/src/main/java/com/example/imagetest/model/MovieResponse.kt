package com.example.imagetest.model

import java.time.Year

data class MovieResponse (
        val title: String,
        val image: String,
        val rating: Double,
        val releaseYear: Int,
        val genre: List<String>
        )