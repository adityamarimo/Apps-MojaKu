package com.moja.mojaku.core.domain.usecase

import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.core.domain.repository.MangaRepositoryImpl
import javax.inject.Inject

class MangaInteractor @Inject constructor(private val mangaRepositoryImpl: MangaRepositoryImpl) :
    MangaUseCase {
    override fun getAllManga() = mangaRepositoryImpl.getAllManga()

    override fun getSearch(
        query: String,
        page: Int,
        limit: Int
    ) = mangaRepositoryImpl.getSearch(
        query,
        page,
        limit
    )

    override fun getFavManga() = mangaRepositoryImpl.getFavManga()

    override suspend fun setFavManga(manga: Manga, state: Boolean) =
        mangaRepositoryImpl.setFavManga(manga, state)
}