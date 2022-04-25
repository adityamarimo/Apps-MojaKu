package com.moja.mojaku.core.di

import com.moja.mojaku.core.data.source.MangaRepository
import com.moja.mojaku.core.domain.repository.MangaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(mangaRepository: MangaRepository): MangaRepositoryImpl

}