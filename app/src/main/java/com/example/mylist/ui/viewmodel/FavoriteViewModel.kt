package com.example.mylist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylist.data.local.FavoriteAnimeDao
import com.example.mylist.data.local.FavoriteAnimeEntity
import com.example.mylist.data.model.FavoriteEvent
import com.example.mylist.data.model.FavoriteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val dao: FavoriteAnimeDao
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state.asStateFlow()

    // expose favorite list (untuk FavoriteScreen)
    val favoriteList = dao.getAllFavoriteAnime()

    fun onEvent(event: FavoriteEvent) {
        when (event) {

            is FavoriteEvent.SaveFavorite -> {
                viewModelScope.launch {
                    val current = state.value

                    val entity = FavoriteAnimeEntity(
                        rank = current.episodes, // ⚠️ sementara (lihat catatan di bawah)
                        title = current.title,
                        type = current.type,
                        episodes = current.episodes,
                        score = null
                    )

                    dao.addToFavorite(entity)

                    _state.value = FavoriteState() // reset input
                }
            }

            is FavoriteEvent.DeleteFavorite -> {
                viewModelScope.launch {
                    val entity = FavoriteAnimeEntity(
                        rank = 0,
                        title = event.title,
                        type = "",
                        episodes = 0,
                        score = null
                    )
                    dao.removeFromFavorite(entity)
                }
            }
        }
    }

    // helper untuk update state dari UI
    fun updateTitle(title: String) {
        _state.value = state.value.copy(title = title)
    }

    fun updateType(type: String) {
        _state.value = state.value.copy(type = type)
    }

    fun updateEpisodes(episodes: Int) {
        _state.value = state.value.copy(episodes = episodes)
    }

    fun addToFavorite(anime: FavoriteAnimeEntity) {
        viewModelScope.launch {
            dao.addToFavorite(anime)
        }
    }
}