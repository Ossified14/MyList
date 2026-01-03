package com.example.mylist.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mylist.data.local.FavoriteAnimeEntity
import com.example.mylist.ui.viewmodel.AnimeViewModel
import com.example.mylist.ui.viewmodel.FavoriteViewModel

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel,
    onNavigateToFavorite: () -> Unit,
    onAddFavorite: (FavoriteAnimeEntity) -> Unit
) {
    Column {
        Button(
            onClick = onNavigateToFavorite,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Go to Favorite")
        }

        LazyColumn {
            items(animeViewModel.animeList) { anime ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .border(1.dp, androidx.compose.ui.graphics.Color.Black),
                    verticalArrangement = Arrangement.spacedBy(space = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(anime.title)
                    Text("Rank: ${anime.rank}")

                    Button(onClick = {
                        onAddFavorite(
                            FavoriteAnimeEntity(
                                rank = anime.rank,
                                title = anime.title,
                                type = anime.type,
                                episodes = anime.episodes,
                                score = anime.score
                            )
                        )
                    }) {
                        Text("Add to Favorite")
                    }
                }
            }
        }
    }
}