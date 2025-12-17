package com.example.mylist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_anime")
data class AnimeFavorite (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val type: String,
    val episodes: Int?,
    val score: Double?,
    val rank: Int
)