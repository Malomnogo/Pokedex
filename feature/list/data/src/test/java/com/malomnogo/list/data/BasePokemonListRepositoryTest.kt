package com.malomnogo.list.data

import androidx.paging.testing.asSnapshot
import com.malomnogo.data.PokemonCloudDataSource
import com.malomnogo.model.PokemonCloud
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class BasePokemonListRepositoryTest {

    private val fakeCloud = FakePokemonCloudDataSource()
    private val mapper = PokemonCloudMapper.ToDomain
    private val repository = BasePokemonListRepository(
        cloudDataSource = fakeCloud,
        mapper = mapper
    )

    @Test
    fun `fetch pokemon list returns mapped data from cloud source`() = runTest {
        fakeCloud.returnItems(
            listOf(
                PokemonCloud(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
                PokemonCloud(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/")
            )
        )

        val result = repository.fetchPokemonList().asSnapshot()

        assertEquals(2, result.size)
        assertEquals("Bulbasaur", result[0].name)
        assertEquals(1, result[0].id)
        assertEquals("Ivysaur", result[1].name)
        assertEquals(2, result[1].id)
    }

    @Test
    fun `fetch pokemon list propagates error`() = runTest {
        val expectedError = RuntimeException("Network error")
        fakeCloud.returnError(expectedError)

        val exception = runCatching {
            repository.fetchPokemonList().asSnapshot()
        }.exceptionOrNull()

        assertNotNull(exception)
        assertEquals("Network error", exception!!.message)
    }

    private class FakePokemonCloudDataSource : PokemonCloudDataSource {
        private val pages = mutableMapOf<Int, List<PokemonCloud>>()
        private var error: Exception? = null

        fun returnItems(page: Int, items: List<PokemonCloud>) {
            pages[page] = items
        }

        fun returnItems(items: List<PokemonCloud>) {
            returnItems(0, items)
        }

        fun returnError(exception: Exception) {
            error = exception
        }

        override suspend fun fetchPokemonList(page: Int): List<PokemonCloud> {
            error?.let { throw it }
            return pages[page] ?: emptyList()
        }
    }
}
