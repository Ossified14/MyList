package com.example.mylist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteAnimeEntity::class],
    version = 1,
    exportSchema = false
)

abstract class MyListDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteAnimeDao
}