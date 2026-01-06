package com.malomnogo.list.data

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.malomnogo.data.PokemonCloudDataSource
import com.malomnogo.domain.PokemonDomain
import com.malomnogo.model.PokemonCloud
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonPagingSourceTest {
    private val fakeCloud = FakePokemonCloudDataSource()
    private val mapper = PokemonCloudMapper.ToDomain
    private val pagingSource =
        PokemonPagingSource(
            cloudDataSource = fakeCloud,
            mapper = mapper,
        )

    @Test
    fun `load returns page when successful load`() =
        runTest {
            fakeCloud.returnItems(
                listOf(
                    PokemonCloud("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                    PokemonCloud("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                ),
            )

            val result = pagingSource.load(refreshParams(key = 0))

            val expected =
                PagingSource.LoadResult.Page(
                    data =
                        listOf(
                            PokemonDomain(1, "Bulbasaur"),
                            PokemonDomain(2, "Ivysaur"),
                        ),
                    prevKey = null,
                    nextKey = 1,
                )

            assertEquals(expected, result)
        }

    @Test
    fun `load returns correct keys for middle page`() =
        runTest {
            fakeCloud.returnItems(
                listOf(
                    PokemonCloud("pidgey", "https://pokeapi.co/api/v2/pokemon/16/"),
                    PokemonCloud("pidgeotto", "https://pokeapi.co/api/v2/pokemon/17/"),
                ),
            )

            val result = pagingSource.load(refreshParams(key = 1))

            val expected =
                PagingSource.LoadResult.Page(
                    data =
                        listOf(
                            PokemonDomain(16, "Pidgey"),
                            PokemonDomain(17, "Pidgeotto"),
                        ),
                    prevKey = 0,
                    nextKey = 2,
                )

            assertEquals(expected, result)
        }

    @Test
    fun `load returns null next key when list is empty`() =
        runTest {
            fakeCloud.returnItems(emptyList())

            val result = pagingSource.load(refreshParams(key = 5))

            val expected =
                PagingSource.LoadResult.Page(
                    data = emptyList(),
                    prevKey = 4,
                    nextKey = null,
                )

            assertEquals(expected, result)
        }

    @Test
    fun `load filters out pokemon with id greater than first generation limit`() =
        runTest {
            fakeCloud.returnItems(
                listOf(
                    PokemonCloud("mew", "https://pokeapi.co/api/v2/pokemon/151/"),
                    PokemonCloud("chikorita", "https://pokeapi.co/api/v2/pokemon/152/"),
                ),
            )

            val result = pagingSource.load(refreshParams())

            val expected =
                PagingSource.LoadResult.Page(
                    data = listOf(PokemonDomain(151, "Mew")),
                    prevKey = null,
                    nextKey = null,
                )

            assertEquals(expected, result)
        }

    @Test
    fun `load returns error when cloud source fails`() =
        runTest {
            val error = RuntimeException("Network error")
            fakeCloud.returnError(error)

            val result = pagingSource.load(refreshParams())

            assertEquals(
                PagingSource.LoadResult.Error<Int, PokemonDomain>(error),
                result,
            )
        }

    @Test
    fun `getRefreshKey returns key from prevKey when available`() {
        val page =
            PagingSource.LoadResult.Page(
                data = listOf(PokemonDomain(1, "Bulbasaur")),
                prevKey = 0,
                nextKey = 2,
            )
        val state = pagingState(page)

        val result = pagingSource.getRefreshKey(state)

        assertEquals(1, result)
    }

    @Test
    fun `getRefreshKey returns key from nextKey when prevKey is null`() {
        val page =
            PagingSource.LoadResult.Page(
                data = listOf(PokemonDomain(1, "Bulbasaur")),
                prevKey = null,
                nextKey = 1,
            )
        val state = pagingState(page)

        val result = pagingSource.getRefreshKey(state)

        assertEquals(0, result)
    }

    private fun refreshParams(key: Int? = 0) =
        PagingSource.LoadParams.Refresh(
            key = key,
            loadSize = 20,
            placeholdersEnabled = false,
        )

    private fun pagingState(page: PagingSource.LoadResult.Page<Int, PokemonDomain>) =
        PagingState(
            pages = listOf(page),
            anchorPosition = 0,
            config = PagingConfig(pageSize = 20),
            leadingPlaceholderCount = 0,
        )

    private class FakePokemonCloudDataSource : PokemonCloudDataSource {
        private var items: List<PokemonCloud> = emptyList()
        private var error: Exception? = null

        fun returnItems(newItems: List<PokemonCloud>) {
            items = newItems
        }

        fun returnError(exception: Exception) {
            error = exception
        }

        override suspend fun fetchPokemonList(page: Int): List<PokemonCloud> {
            error?.let { throw it }
            return items
        }
    }
}
