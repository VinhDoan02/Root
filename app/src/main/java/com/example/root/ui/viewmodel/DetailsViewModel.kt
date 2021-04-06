package com.example.root.ui.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

    val photoSource = ObservableField<String>()

    init{
        Log.d("detailviewmodel" , "set source")
        photoSource.set(PHOTO_SOURCE)
        photoSource.notifyChange()
    }

}


private const val PHOTO_SOURCE = "https://source.unsplash.com/random"