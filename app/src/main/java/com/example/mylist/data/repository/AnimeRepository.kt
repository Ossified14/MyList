package com.example.mylist.data.repository

import com.example.mylist.data.model.Anime
import com.example.mylist.data.remote.RetrofitClient

class AnimeRepository {
    suspend fun getTopAnime(): List<Anime> {
        return RetrofitClient.api.getTopAnime().data
    }
}