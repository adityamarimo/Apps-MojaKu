package com.moja.mojaku.di

import com.moja.mojaku.core.domain.usecase.MangaUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun mangaUseCase(): MangaUseCase
}