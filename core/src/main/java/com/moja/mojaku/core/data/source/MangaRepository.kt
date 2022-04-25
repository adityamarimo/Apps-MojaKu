package com.moja.mojaku.core.data.source

import com.moja.mojaku.core.data.source.local.LocalDataSource
import com.moja.mojaku.core.data.source.remote.RemoteDataSource
import com.moja.mojaku.core.data.source.remote.network.ApiResponse
import com.moja.mojaku.core.data.source.remote.response.MangaResponsesData
import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.core.domain.repository.MangaRepositoryImpl
import com.moja.mojaku.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MangaRepositoryImpl {

    override fun getAllManga(): Flow<Resource<List<Manga>>> =
        object : NetworkBoundResource<List<Manga>, List<MangaResponsesData>>() {
            override fun loadFromDB(): Flow<List<Manga>> {
                return localDataSource.getAllManga().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Manga>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MangaResponsesData>>> =
                remoteDataSource.getAllManga()

            override suspend fun saveCallResult(data: List<MangaResponsesData>) {
                val mangaList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertManga(mangaList)
            }
        }.asFlow()

    override fun getSearch(query: String, page: Int, limit: Int): Flow<Resource<List<Manga>>> {
        return flow {
            emit(Resource.Loading())

            remoteDataSource.getSearch(query, page, limit).collect { response ->
                when (response) {
                    is ApiResponse.Success -> emit(
                        Resource.Success(
                            DataMapper.mapSearchToDomain(
                                response.data
                            )
                        )
                    )
                    is ApiResponse.Empty -> emit(Resource.Error(null, null))
                    is ApiResponse.Error -> emit(Resource.Error(response.errorMessage, null))
                }
            }
        }
    }

    override fun getFavManga(): Flow<List<Manga>> {
        return localDataSource.getFavManga().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavManga(manga: Manga, state: Boolean) {
        val mangaEntity = DataMapper.mapDomainToEntity(manga)
        localDataSource.setFavManga(mangaEntity, state)
    }
}