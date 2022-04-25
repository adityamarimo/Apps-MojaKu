package com.moja.mojaku.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moja.mojaku.core.domain.usecase.MangaUseCase

class FavoriteViewModel(mangaUseCase: MangaUseCase) : ViewModel() {
    val manga = mangaUseCase.getFavManga().asLiveData()
}