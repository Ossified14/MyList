package com.example.mylist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylist.data.local.entity.AnimeFavorite
import java.sql.Time

@Dao
interface AnimeFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(anime: AnimeFavorite)

    @Query("SELECT * FROM favorite_anime")
    suspend fun getAllFavorites(): List<AnimeFavorite>

    @Query("DELETE FROM favorite_anime WHERE id = :title")
    suspend fun deleteFavorite(title: String)

    @Query("SELECT * FROM favorite_anime WHERE id = :title")
    suspend fun getFavoriteById(title: String): Boolean
}