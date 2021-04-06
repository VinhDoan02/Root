package com.example.root.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchResultApi {

    @GET("api.php")
    fun getSearchResult(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") srsearch: String
    ): Single<SearchResultApiResponse>
}