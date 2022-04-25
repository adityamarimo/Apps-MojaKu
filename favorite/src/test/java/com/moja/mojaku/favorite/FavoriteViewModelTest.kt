package com.moja.mojaku.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moja.mojaku.core.domain.usecase.MangaUseCase
import com.moja.mojaku.utils.DummyData
import com.moja.mojaku.utils.mangaObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class FavoriteViewModelTest{
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
    fun `get favorite manga from database, return list manga`() {
        val useCase = Mockito.mock(MangaUseCase::class.java)
        val flow = flowOf(DummyData.listManga())

        Mockito.`when`(useCase.getFavManga())
            .thenReturn(flow)

        val viewModel = FavoriteViewModel(useCase)

        val result = viewModel.manga.mangaObserver().observedValue

        Mockito.verify(useCase, Mockito.atLeastOnce()).getFavManga()
        Mockito.verifyNoMoreInteractions(useCase)
        Assert.assertNotNull(result)
    }
}