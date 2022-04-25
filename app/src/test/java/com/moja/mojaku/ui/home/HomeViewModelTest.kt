package com.moja.mojaku.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moja.mojaku.core.data.source.Resource
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
class HomeViewModelTest {

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
    fun `get manga from api, return list manga`() {
        val useCase = Mockito.mock(MangaUseCase::class.java)
        val flow = flowOf(Resource.Success(DummyData.listManga()))

        Mockito.`when`(useCase.getAllManga())
            .thenReturn(flow)

        val viewModel = HomeViewModel(useCase)

        val result = viewModel.manga.mangaObserver().observedValue

        Mockito.verify(useCase, Mockito.atLeastOnce()).getAllManga()
        Mockito.verifyNoMoreInteractions(useCase)
        Assert.assertNotNull(result)
    }
}