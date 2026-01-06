package com.malomnogo.lsit.presentation

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import com.malomnogo.domain.PokemonDomain
import com.malomnogo.domain.PokemonListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class PokemonListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: FakePokemonListRepository
    private lateinit var itemMapper: FakePokemonMapper
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var differ: AsyncPagingDataDiffer<PokemonUiItem>

    @Before
    fun setup() {
        repository = FakePokemonListRepository()
        itemMapper = FakePokemonMapper()
        differ = pagingDiffer()
    }

    private fun createViewModel() {
        viewModel = PokemonListViewModel(
            repository = repository,
            itemMapper = itemMapper
        )
    }

    @Test
    fun `pokemonList emits mapped paging data`() = runTest {
        repository.returnItems(
            listOf(
                PokemonDomain(1, "Bulbasaur"),
                PokemonDomain(4, "Charmander"),
                PokemonDomain(7, "Squirtle")
            )
        )

        createViewModel()

        differ.submitData(viewModel.pokemonList.first())
        advanceUntilIdle()

        val items = differ.snapshot().items
        assertEquals(listOf(1, 4, 7), items.map { it.id })
    }

    @Test
    fun `pokemonList emits empty list when repository returns empty paging data`() = runTest {
        repository.returnEmpty()
        createViewModel()

        differ.submitData(viewModel.pokemonList.first())
        advanceUntilIdle()

        assertTrue(differ.snapshot().items.isEmpty())
    }

    @Test
    fun `itemMapper is called for each domain item`() = runTest {
        val domainItems = listOf(
            PokemonDomain(1, "Bulbasaur"),
            PokemonDomain(4, "Charmander"),
            PokemonDomain(7, "Squirtle")
        )
        repository.returnItems(domainItems)
        createViewModel()

        differ.submitData(viewModel.pokemonList.first())
        advanceUntilIdle()

        assertEquals(3, itemMapper.callCount)
        assertEquals(domainItems, itemMapper.mappedItems)
    }
}

private class FakePokemonMapper : PokemonItemMapper {
    var callCount = 0
        private set
    val mappedItems = mutableListOf<PokemonDomain>()

    override fun map(input: PokemonDomain): PokemonUiItem {
        callCount++
        mappedItems += input
        return PokemonUiItem(id = input.id, name = input.name, imageUrl = "stub", number = "stub")
    }
}

private class FakePokemonListRepository : PokemonListRepository {

    private var pagingData: PagingData<PokemonDomain> = PagingData.from(emptyList())

    fun returnItems(items: List<PokemonDomain>) {
        pagingData = PagingData.from(items)
    }

    fun returnEmpty() {
        pagingData = PagingData.from(emptyList())
    }

    override fun fetchPokemonList(): Flow<PagingData<PokemonDomain>> = flowOf(pagingData)
}
