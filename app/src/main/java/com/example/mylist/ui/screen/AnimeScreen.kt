package com.example.mylist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mylist.data.local.database.AppDatabase
import com.example.mylist.data.local.entity.AnimeFavorite
import com.example.mylist.data.repository.AnimeLocalRepository
import com.example.mylist.data.repository.AnimeRepository
import com.example.mylist.ui.viewmodel.AnimeViewModel
import com.example.mylist.ui.viewmodel.AnimeViewModelFactory

@Composable
fun AnimeScreen() {

    // ===== SETUP DEPENDENCY =====
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val dao = database.animeFavoriteDao()

    val localRepository = AnimeLocalRepository(dao)
    val repository = AnimeRepository()

    val factory = AnimeViewModelFactory(
        repository,
        localRepository
    )

    val viewModel: AnimeViewModel = viewModel(factory = factory)

    // ===== LOAD FAVORITE SEKALI =====
    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    val favorites = viewModel.favorites.value

    fun isFavorite(title: String): Boolean {
        return favorites.any { it.title == title }
    }

    // ===== UI =====
    LazyColumn {

        // ===== FAVORITES SECTION =====
        if (favorites.isNotEmpty()) {
            item {
                Text(
                    text = "❤️ Favorites",
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(favorites) { fav ->
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(text = fav.title)
                    Text(text = "Episode: ${fav.episodes}")
                    Text(text = "Score: ${fav.score}")
                }
            }
        }

        // ===== ALL ANIME SECTION =====
        item {
            Text(
                text = "📺 All Anime",
                modifier = Modifier.padding(16.dp)
            )
        }

        items(viewModel.animeList) { anime ->

            val favorite = isFavorite(anime.title)

            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = anime.title)
                    Text(text = "Episode: ${anime.episodes}")
                    Text(text = "Score: ${anime.score}")
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        if (favorite) {
                            viewModel.removeFavorites(anime.title)
                        } else {
                            viewModel.addFavorites(
                                AnimeFavorite(
                                    title = anime.title,
                                    type = anime.type,
                                    episodes = anime.episodes,
                                    score = anime.score,
                                    rank = anime.rank
                                )
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (favorite)
                            Icons.Default.Favorite
                        else
                            Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (favorite) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}