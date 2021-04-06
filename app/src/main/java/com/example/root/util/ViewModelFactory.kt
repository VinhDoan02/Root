package com.example.root.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.root.domain.SearchRepository
import com.example.root.ui.viewmodel.DetailsViewModel
import com.example.root.ui.viewmodel.MainViewModel

class MainViewModelFactory(val repo : SearchRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        return MainViewModel(repo) as T
    }
}

class DetailViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        return DetailsViewModel() as T
    }
}