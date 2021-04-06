package com.example.root.ui.viewmodel

import androidx.databinding.ObservableField
import com.example.root.domain.SearchItem
import com.example.root.util.ItemViewModel
import io.reactivex.subjects.PublishSubject

class SearchItemViewModel (
    val item : SearchItem,
    private val events : PublishSubject<MainViewModel.MainViewModelEvent>
) : ItemViewModel {
    val pageId = ObservableField<String>()
    val size = ObservableField<String>()
    val wordCount = ObservableField<String>()

    init{
        pageId.set("PageId: " + item.pageid)
        size.set("Size: " + item.size)
        wordCount.set("Wordcount: " + item.wordcount )
    }

    fun onItemClick() {
        events.onNext(MainViewModel.MainViewModelEvent.OnItemClick(item))
    }

}
