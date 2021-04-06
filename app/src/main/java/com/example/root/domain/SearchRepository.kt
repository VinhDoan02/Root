package com.example.root.domain

import io.reactivex.Single

interface SearchRepository {
    fun getSearchResult(wordQuery : String): Single<SearchResult>
}