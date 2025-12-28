package com.example.mylist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(anime: FavoriteAnimeEntity)

    @Delete
    suspend fun removeFromFavorite(anime: FavoriteAnimeEntity)

    @Query("SELECT * FROM favorite_anime ORDER BY rank ASC")
    fun getAllFavoriteAnime(): Flow<List<FavoriteAnimeEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_anime WHERE rank = :rank)")
    suspend fun isFavorite(rank: Int): Boolean
}