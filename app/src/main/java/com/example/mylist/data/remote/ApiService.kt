package com.example.mylist.data.remote

import com.example.mylist.data.model.AnimeResponse
import retrofit2.http.GET

interface ApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): AnimeResponse
}