package com.example.mylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.mylist.data.local.MyListDatabase
import com.example.mylist.ui.screen.AnimeScreen
import com.example.mylist.ui.viewmodel.AnimeViewModel
import com.example.mylist.ui.viewmodel.FavoriteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mylist.data.model.FavoriteEvent
import com.example.mylist.ui.navigation.MyListNavHost
import com.example.mylist.ui.navigation.Screen
import com.example.mylist.ui.screen.FavoriteScreen
import com.example.mylist.ui.viewmodel.FavoriteViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
            applicationContext,
            MyListDatabase::class.java,
            "mylist_db"
        ).build()

        val favoriteDao = database.favoriteDao()

        setContent {

            val favoriteViewModel: FavoriteViewModel = viewModel(
                factory = FavoriteViewModelFactory(favoriteDao)
            )

            val animeViewModel: AnimeViewModel = viewModel()

            MyListNavHost(
                animeViewModel = animeViewModel,
                favoriteViewModel = favoriteViewModel
            )
        }
    }
}