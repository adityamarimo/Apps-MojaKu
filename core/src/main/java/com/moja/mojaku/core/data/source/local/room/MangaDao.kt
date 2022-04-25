package com.moja.mojaku.core.data.source.local.room

import androidx.room.*
import com.moja.mojaku.core.data.source.local.entity.MangaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MangaDao {

    @Query("SELECT * FROM manga")
    fun getAllManga(): Flow<List<MangaEntity>>

    @Query("SELECT * FROM manga where isFavorite = 1")
    fun getFavManga(): Flow<List<MangaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManga(manga: List<MangaEntity>)

    @Update
    fun updateFavManga(manga: MangaEntity)
}