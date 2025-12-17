package com.example.mylist.data.model

import android.icu.text.CaseMap

data class Anime (
    val title: String,
    val type: String,
    val episodes: Int,
    val score: Double?,
    val rank: Int
)