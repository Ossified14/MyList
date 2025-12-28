package com.example.mylist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mylist.ui.screen.AnimeScreen
import com.example.mylist.ui.screen.FavoriteScreen
import com.example.mylist.ui.viewmodel.AnimeViewModel
import com.example.mylist.ui.viewmodel.FavoriteViewModel

@Composable
fun MyListNavHost(
    animeViewModel: AnimeViewModel,
    favoriteViewModel: FavoriteViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Anime.route
    ) {

        composable(Screen.Anime.route) {
            AnimeScreen(
                animeViewModel = animeViewModel,
                onNavigateToFavorite = {
                    navController.navigate(Screen.Favorite.route)
                },
                onAddFavorite = { entity ->
                    favoriteViewModel.addToFavorite(entity)
                }
            )
        }

        composable(Screen.Favorite.route) {
            FavoriteScreen(
                viewModel = favoriteViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}