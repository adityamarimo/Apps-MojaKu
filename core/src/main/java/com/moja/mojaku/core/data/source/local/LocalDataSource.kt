package com.moja.mojaku.core.data.source.local

import com.moja.mojaku.core.data.source.local.entity.MangaEntity
import com.moja.mojaku.core.data.source.local.room.MangaDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mangaDao: MangaDao) {

    fun getAllManga(): Flow<List<MangaEntity>> = mangaDao.getAllManga()

    fun getFavManga(): Flow<List<MangaEntity>> = mangaDao.getFavManga()

    suspend fun insertManga(mangaList: List<MangaEntity>) =
        mangaDao.insertManga(mangaList)

    suspend fun setFavManga(manga: MangaEntity, status: Boolean) {
        manga.isFavorite = status
        mangaDao.updateFavManga(manga)
    }
}