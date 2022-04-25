package com.moja.mojaku.ui.detail

import androidx.lifecycle.ViewModel
import com.moja.mojaku.core.domain.model.Manga
import com.moja.mojaku.core.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMangaViewModel @Inject constructor(private val mangaUseCase: MangaUseCase) :
    ViewModel() {

    fun setFavManga(manga: Manga, newStatus: Boolean) {
        mangaUseCase.setFavManga(manga, newStatus)
    }

}