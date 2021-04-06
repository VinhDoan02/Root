package com.example.root.ui.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.root.R
import com.example.root.domain.SearchItem
import com.example.root.domain.SearchRepository
import com.example.root.domain.SearchResult
import com.example.root.util.RecyclerViewItemWrapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(val repo : SearchRepository) : ViewModel() {

    private val disposable = CompositeDisposable()
    val searchItemWrapper = ObservableField<RecyclerViewItemWrapper>()

    //eventing
    private val uiEvent = PublishSubject.create<MainViewModelEvent>()
    val events: Observable<MainViewModelEvent> = uiEvent.hide()

    init{
        Log.d("testroot" , "querying")
        querySearch("Tesla")
    }

    fun querySearch(wordQuery: String) {
        disposable.add(
            repo.getSearchResult(wordQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result -> handleResult(result)},
                    {error -> handleError(error)}
                )
        )
    }


    fun handleResult(searchResult: SearchResult) {
        Log.d("testroot" , "searchResult " + searchResult.query.search[0].title)
        createViewModel(searchResult.query.search)
    }

    fun handleError(throwable: Throwable) {
        Log.d("testroot" , "in error " + throwable.message)
    }

    private fun createViewModel(searchItemList : List<SearchItem>) {
        var viewModelList = ArrayList<SearchItemViewModel>()
        for(item in searchItemList) {
            viewModelList.add(SearchItemViewModel(item,uiEvent))
        }
        var itemWrapper = RecyclerViewItemWrapper(
            R.layout.search_item,
            viewModelList
        )
        searchItemWrapper.set(itemWrapper)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    sealed class MainViewModelEvent {
        class OnItemClick(val item : SearchItem) : MainViewModelEvent()
    }

}
