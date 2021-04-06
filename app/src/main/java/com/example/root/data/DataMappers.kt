package com.example.root.data

import com.example.root.domain.Continue
import com.example.root.domain.Query
import com.example.root.domain.SearchInfo
import com.example.root.domain.SearchItem

internal fun ContinueData.toDomain() : Continue {
    return Continue(
        sroffset,
        continueStringData
    )
}

internal fun QueryData.toDomain() : Query {
    return Query(
        searchinfo.toDomain(),
        search.map { SearchItem -> SearchItem.toDomain() }
    )
}

internal fun SearchInfoData.toDomain() : SearchInfo {
    return SearchInfo( totalhits )
}

internal fun SearchItemData.toDomain() : SearchItem {
    return SearchItem(
        ns,
        title,
        pageid,
        size,
        wordcount,
        snippet,
        timestamp
    )
}
