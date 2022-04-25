package com.moja.mojaku.core.data.source.remote.network

import com.moja.mojaku.core.BuildConfig
import com.moja.mojaku.core.data.source.remote.response.MangaResponses
import com.moja.mojaku.core.data.source.remote.response.SearchResponses
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(BuildConfig.MANGA)
    suspend fun getList(): MangaResponses

    @GET(BuildConfig.SEARCH)
    suspend fun getSearch(
        @Query("q") query: String?,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
    ): SearchResponses
}