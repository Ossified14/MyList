package com.example.mylist.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylist.data.model.Anime
import com.example.mylist.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {
    var animeList by mutableStateOf<List<Anime>>(emptyList())
        private set

    init {
        fetchTopAnime()
    }

    private fun fetchTopAnime() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getTopAnime()
                animeList = response.data
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}