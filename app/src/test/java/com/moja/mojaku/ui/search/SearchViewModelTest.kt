package com.moja.mojaku.ui.search

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
import org.mockito.Mockito.*


@ExperimentalCoroutinesApi
class SearchViewModelTest {
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
    fun `get search manga from api, return list manga`() {
        val query = "Berserk"
        val limit = 10
        val page = 1
        val useCase = mock(MangaUseCase::class.java)
        val flow = flowOf(Resource.Success(DummyData.listManga()))

        /*val testUseCase = Mockito.mock(MangaUseCase::class.java)

        val manga = Manga()
        doNothing().`when`(testUseCase).setFavManga(manga, true)
        verify(testUseCase, atLeastOnce()).getFavManga()*/

        `when`(useCase.getSearch(query, page, limit))
            .thenReturn(flow)

        val viewModel = SearchViewModel(useCase)

        val result = viewModel.search(query).mangaObserver().observedValue

        verify(useCase, atLeastOnce()).getSearch(query, page, limit)
        verifyNoMoreInteractions(useCase)
        Assert.assertNotNull(result)

    }
}