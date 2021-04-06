package com.example.root.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SearchResultApiResponse (
    val batchcomplete: String?,
    @field:Json(name = "continue") val continueData : ContinueData,
    val query : QueryData
)

@JsonClass(generateAdapter = true)
class ContinueData(
    val sroffset : Int?,
    @field:Json(name = "continue") val continueStringData : String?
)

@JsonClass(generateAdapter = true)
class QueryData(
    val searchinfo : SearchInfoData,
    val search : List<SearchItemData> = emptyList()
)

@JsonClass(generateAdapter = true)
class SearchInfoData(
    val totalhits : Int?
)

@JsonClass(generateAdapter = true)
class SearchItemData (
    val ns : Int?,
    val title : String?,
    val pageid : Int?,
    val size : Int?,
    val wordcount : Int?,
    val snippet: String?,
    val timestamp: String?
)