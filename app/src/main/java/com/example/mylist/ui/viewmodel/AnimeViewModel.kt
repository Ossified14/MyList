package com.example.mylist.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylist.data.local.entity.AnimeFavorite
import com.example.mylist.data.model.Anime
import com.example.mylist.data.repository.AnimeLocalRepository
import com.example.mylist.data.repository.AnimeRepository
import kotlinx.coroutines.launch

class AnimeViewModel (
    private val repository: AnimeRepository,
    private val localRepository: AnimeLocalRepository
) : ViewModel() {
    var animeList by mutableStateOf<List<Anime>>(emptyList())
        private set

    init {
        fetchTopAnime()
        loadFavorites()
    }

    private fun fetchTopAnime() {
        viewModelScope.launch {
            try {
                animeList = repository.getTopAnime()
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }

    private val _favorites = mutableStateOf<List<AnimeFavorite>>(emptyList())
    val favorites: State<List<AnimeFavorite>> = _favorites

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = localRepository.getAllFavorites()
        }
    }

    fun addFavorites(anime: AnimeFavorite) {
        viewModelScope.launch {
            localRepository.addToFavorite(anime)
            loadFavorites()
        }
    }

    fun removeFavorites (title: String) {
        viewModelScope.launch {
            localRepository.deleteFavorite(title)
            loadFavorites()
        }
    }
}