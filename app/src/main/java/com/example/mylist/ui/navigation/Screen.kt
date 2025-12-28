package com.example.mylist.ui.navigation

sealed class Screen(val route: String) {
    object Anime : Screen("anime")
    object Favorite : Screen("favorite")
}