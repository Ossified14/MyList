package com.example.mylist.data.repository

import com.example.mylist.data.local.dao.AnimeFavoriteDao
import com.example.mylist.data.local.entity.AnimeFavorite

class AnimeLocalRepository (
    private val dao: AnimeFavoriteDao
) {

    suspend fun addToFavorite(anime: AnimeFavorite) {
        dao.insertFavorite(anime)
    }
    suspend fun getAllFavorites(): List<AnimeFavorite> {
        return dao.getAllFavorites()
    }

    suspend fun deleteFavorite(title: String) {
        dao.deleteFavorite(title)
    }

    suspend fun isFavorite(title: String): Boolean {
        return dao.getFavoriteById(title)
    }
}