package com.example.mylist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_anime")
data class FavoriteAnimeEntity(
    @PrimaryKey
    val rank: Int,
    val title: String,
    val type: String,
    val episodes: Int,
    val score: Double?
)
