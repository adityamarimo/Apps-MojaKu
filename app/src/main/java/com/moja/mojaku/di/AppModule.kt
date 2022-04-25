package com.moja.mojaku.di

import com.moja.mojaku.core.domain.usecase.MangaInteractor
import com.moja.mojaku.core.domain.usecase.MangaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMangaUseCase(mangaInteractor: MangaInteractor): MangaUseCase

}