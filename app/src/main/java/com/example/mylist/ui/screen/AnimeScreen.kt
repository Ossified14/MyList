package com.example.mylist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mylist.ui.viewmodel.AnimeViewModel


@Composable
fun AnimeScreen(
    viewModel: AnimeViewModel = AnimeViewModel()
) {
    LazyColumn {
        items(viewModel.animeList) { anime ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = anime.title)
                Text(text = "Type: ${anime.type}")
                Text(text = "Episode: ${anime.episodes}")
                Text(text = "Score: ${anime.score}")
                Text(text = "Rank: ${anime.rank}")
            }
        }
    }
}