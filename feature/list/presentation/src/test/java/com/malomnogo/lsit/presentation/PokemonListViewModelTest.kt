package com.malomnogo.lsit.presentation

import com.malomnogo.lsit.TestProvideDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class PokemonListViewModelTest {

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var repository: FakePokemonListRepository

    @Before
    fun setup() {
        repository = FakePokemonListRepository()
        viewModel = PokemonListViewModel(
            repository = repository,
            mapper = BasePokemonListDomainToUiMapper(pokemonMapper = BasePokemonDomainToUiMapper()),
            provideDispatchers = TestProvideDispatcher(StandardTestDispatcher())
        )
    }

    @Test
    fun `success first time`() = runTest {
        repository.returnSuccess()
        val actual: StateFlow<PokemonListUiState> = viewModel.uiState
        val expected = PokemonListUiState.Base(
            pokemonList = listOf<PokemonUiItem>(
                PokemonUiItem(
                    id = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg"
                ),
                PokemonUiItem(
                    id = 4,
                    name = "Charmander",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg"
                ),
                PokemonUiItem(
                    id = 7,
                    name = "Squirtle",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/7.svg"
                )
            )
        )

        viewModel.loadData()
        assertEquals(
            expected = PokemonListUiState.Progress,
            actual = actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected = PokemonListUiState.Base(pokemonList = expected),
            actual = actual.value
        )
    }

    @Test
    fun `error twice`() = runTest {
        repository.returnError()
        val actual: StateFlow<PokemonListUiState> = viewModel.uiState
        val expected = PokemonListUiState.Error(message = "No internet connection")
        viewModel.loadData()
        assertEquals(
            expected = PokemonListUiState.Progress,
            actual = actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected = expected,
            actual = actual.value
        )

        viewModel.loadData()
        assertEquals(
            expected = PokemonListUiState.Progress,
            actual = actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected = expected,
            actual = actual.value
        )
    }

    @Test
    fun `success after error`() = runTest {
        repository.returnError()
        val actual: StateFlow<PokemonListUiState> = viewModel.uiState
        var expected = PokemonListUiState.Error(message = "No internet connection")
        viewModel.loadData()
        assertEquals(
            expected = PokemonListUiState.Progress,
            actual = actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected = expected,
            actual = actual.value
        )

        expected = PokemonListUiState.Base(
            pokemonList = listOf<PokemonUiItem>(
                PokemonUiItem(
                    id = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg"
                ),
                PokemonUiItem(
                    id = 4,
                    name = "Charmander",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg"
                ),
                PokemonUiItem(
                    id = 7,
                    name = "Squirtle",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/7.svg"
                )
            )
        )
        viewModel.loadData()
        assertEquals(
            expected = PokemonListUiState.Progress,
            actual = actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected = expected,
            actual = actual.value
        )
    }
}

private class FakePokemonListRepository : PokemonRepository {

    lateinit var result: PokemonListResult

    fun returnSuccess() {
        result = PokemonListResult.Succes(
            pokemonList = PokemonDomain(
                id = 1,
                name = "Bulbasaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg"
            ),
            PokemonDomain(
                id = 4,
                name = "Charmander",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg"
            ),
            PokemonDomain(
                id = 7,
                name = "Squirtle",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/7.svg"
            )
        )
    }

    fun returnError() {
        result = PokemonListResult.Error(message = "No internet connection")
    }

    override suspend fun fetchPokemonList(): List<PokemonDomain> {
        return result
    }
}
