package com.malomnogo.data

import com.malomnogo.model.PokemonCloud
import com.malomnogo.model.core.BasePagingResponse
import com.malomnogo.network.PokemonService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonCloudDataSourceTest {
    private val service = FakePokemonService()
    private val dataSource = PokemonCloudDataSource.Base(service)

    @Test
    fun `fetch pokemon list calls service with correct offset and limit`() =
        runTest {
            val page = 2
            val expectedOffset = 40
            val expectedLimit = 20

            dataSource.fetchPokemonList(page)

            assertEquals(expectedOffset, service.lastOffset)
            assertEquals(expectedLimit, service.lastLimit)
        }

    @Test
    fun `fetch pokemon list returns results from service`() =
        runTest {
            val expectedList =
                listOf(
                    PokemonCloud(name = "bulbasaur", url = "url1"),
                    PokemonCloud(name = "ivysaur", url = "url2"),
                )
            service.returnResponse(
                BasePagingResponse(
                    count = 100,
                    next = "next_url",
                    previous = "prev_url",
                    results = expectedList,
                ),
            )

            val result = dataSource.fetchPokemonList(0)

            assertEquals(expectedList, result)
        }

    private class FakePokemonService : PokemonService {
        var lastOffset: Int? = null
            private set
        var lastLimit: Int? = null
            private set

        private var responseToReturn =
            BasePagingResponse<PokemonCloud>(
                count = 0,
                next = null,
                previous = null,
                results = emptyList(),
            )

        fun returnResponse(response: BasePagingResponse<PokemonCloud>) {
            responseToReturn = response
        }

        override suspend fun fetchPokemonList(
            offset: Int,
            limit: Int,
        ): BasePagingResponse<PokemonCloud> {
            lastOffset = offset
            lastLimit = limit
            return responseToReturn
        }
    }
}
