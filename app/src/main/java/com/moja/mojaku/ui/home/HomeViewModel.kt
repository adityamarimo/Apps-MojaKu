package com.moja.mojaku.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moja.mojaku.core.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(mangaUseCase: MangaUseCase) : ViewModel() {
    val manga = mangaUseCase.getAllManga().asLiveData()
}