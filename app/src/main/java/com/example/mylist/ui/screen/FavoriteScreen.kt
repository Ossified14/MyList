package com.example.mylist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mylist.ui.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onBack: () -> Unit
) {
    val favorites = viewModel.favoriteList.collectAsState(initial = emptyList())

    Column {
        Column{
            Button(
                onClick = onBack,
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                Text("Top Anime")
            }
        }

        LazyColumn {
            items(favorites.value) { anime ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(anime.title)
                    Text("Episode: ${anime.episodes}")
                }
            }
        }
    }
}
