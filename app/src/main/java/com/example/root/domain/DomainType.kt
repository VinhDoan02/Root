package com.example.root.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    val batchcomplete: String?,
    val continueData : Continue,
    val query : Query
): Parcelable

@Parcelize
data class Continue(
    val sroffset : Int?,
    val continueString : String?
): Parcelable

@Parcelize
data class Query(
    val searchinfo : SearchInfo,
    val search : List<SearchItem>
): Parcelable

@Parcelize
data class SearchInfo(
    val totalhits : Int?
): Parcelable

@Parcelize
data class SearchItem(
    val ns : Int?,
    val title : String?,
    val pageid : Int?,
    val size : Int?,
    val wordcount : Int?,
    val snippet: String?,
    val timestamp: String?
) : Parcelable
