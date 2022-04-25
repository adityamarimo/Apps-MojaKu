package com.moja.mojaku.core.domain.repository

import com.moja.mojaku.core.data.source.Resource
import com.moja.mojaku.core.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepositoryImpl {
    fun getAllManga(): Flow<Resource<List<Manga>>>

    fun getSearch(
        query: String,
        page: Int,
        limit: Int
    ): Flow<Resource<List<Manga>>>

    fun getFavManga(): Flow<List<Manga>>

    fun setFavManga(manga: Manga, state: Boolean)
}