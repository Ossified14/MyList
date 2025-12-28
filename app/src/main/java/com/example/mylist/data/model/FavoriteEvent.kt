package com.example.mylist.data.model

sealed interface FavoriteEvent {
    object SaveFavorite : FavoriteEvent
    data class DeleteFavorite(val title: String) : FavoriteEvent
}