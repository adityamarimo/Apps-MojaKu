package com.moja.mojaku.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.core.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMangaViewModel @Inject constructor(private val mangaUseCase: MangaUseCase) :
    ViewModel() {

    fun setFavManga(manga: Manga, newStatus: Boolean) {
        viewModelScope.launch {
            mangaUseCase.setFavManga(manga, newStatus)
        }
    }

}