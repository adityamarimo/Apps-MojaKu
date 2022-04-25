package com.moja.mojaku.core.data.source.remote

import android.util.Log
import com.moja.mojaku.core.data.source.remote.network.ApiResponse
import com.moja.mojaku.core.data.source.remote.network.ApiService
import com.moja.mojaku.core.data.source.remote.response.MangaResponsesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllManga(): Flow<ApiResponse<List<MangaResponsesData>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearch(
        query: String,
        page: Int,
        limit: Int
    ): Flow<ApiResponse<List<MangaResponsesData>>> {
        return flow {
            try {
                val response = apiService.getSearch(query, page, limit)
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}