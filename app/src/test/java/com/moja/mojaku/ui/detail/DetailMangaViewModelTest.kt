package com.moja.mojaku.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moja.mojaku.core.domain.usecase.MangaUseCase
import com.moja.mojaku.utils.DummyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class DetailMangaViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should called method set fav manga at least once with correct argument`() {
        val useCase = Mockito.mock(MangaUseCase::class.java)
        val dummyManga = DummyData.listManga().first()

        val viewModel = DetailMangaViewModel(useCase)
        viewModel.setFavManga(dummyManga, true)

        Mockito.verify(useCase, Mockito.atLeastOnce()).setFavManga(dummyManga, true)
        Mockito.verifyNoMoreInteractions(useCase)
    }
}