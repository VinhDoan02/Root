package com.example.root.data

import com.example.root.domain.SearchRepository
import com.example.root.domain.SearchResult
import io.reactivex.Single

class SearchRepositoryImpl(
    private val api: SearchResultApi
) : SearchRepository {
    override fun getSearchResult(wordQuery: String): Single<SearchResult> {
        return api.getSearchResult("query","json","search",wordQuery)
            .map{ response ->
                SearchResult(
                    response.batchcomplete,
                    response.continueData.toDomain(),
                    response.query.toDomain()
                )

            }
    }

}