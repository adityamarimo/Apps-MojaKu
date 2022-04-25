package com.moja.mojaku.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moja.mojaku.core.data.source.Resource
import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.core.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val mangaUseCase: MangaUseCase) : ViewModel() {
    fun search(query: String): LiveData<Resource<List<Manga>>> {
        val page = 1
        val limit = 10

        return mangaUseCase.getSearch(query, page, limit).asLiveData()
    }
}