package com.example.mylist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mylist.data.repository.AnimeLocalRepository
import com.example.mylist.data.repository.AnimeRepository

class AnimeViewModelFactory (
    private val repository: AnimeRepository,
    private val localRepository: AnimeLocalRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeViewModel::class.java)) {
            return AnimeViewModel(repository, localRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}