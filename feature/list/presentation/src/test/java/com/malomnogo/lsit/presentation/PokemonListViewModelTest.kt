package com.malomnogo.lsit.presentation

import com.malomnogo.lsit.core.FakeRunAsync
import kotlinx.coroutines.flow.StateFlow
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class PokemonListViewModelTest {

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var repository: FakePokemonListRepository
    private lateinit var runAsync: FakeRunAsync

    @Before
    fun setup() {
        runAsync = FakeRunAsync()
        repository = FakePokemonListRepository()
        viewModel = PokemonListViewModel(
            repository = repository,
            mapper = BasePokemonListDomainToUiMapper(
                pokemonMapper = BasePokemonDomainToUiMapper()
            ),
            runAsync = runAsync,
        )
    }

    @Test
    fun `success first time`() {
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
            actual = actual
        )
        runAsync.returnResult()
        assertEquals(
            expected = PokemonListUiState.Base(pokemonList = expected),
            actual = actual
        )
    }

    @Test
    fun `error twice`() {
        repository.returnError()
        val actual: StateFlow<PokemonListUiState> = viewModel.uiState
        val expected = PokemonListUiState.Error(message = "No internet connection")
        viewModel.loadData()
        assertEquals(
            expected = PokemonListUiState.Progress,
            actual = actual
        )
        runAsync.retrunResult()
        assertEquals(
            expected = expected,
            actual = actual
        )

        viewModel.loadData()
        assertEquals(
            expected = PokemonListUiState.Progress,
            actual = actual
        )
        runAsync.retrunResult()
        assertEquals(
            expected = expected,
            actual = actual
        )
    }

    @Test
    fun `success after error`() {
        repository.returnError()
        val actual: StateFlow<PokemonListUiState> = viewModel.uiState
        var expected = PokemonListUiState.Error(message = "No internet connection")
        viewModel.loadData()
        assertEquals(
            expected = PokemonListUiState.Progress,
            actual = actual
        )
        runAsync.retrunResult()
        assertEquals(
            expected = expected,
            actual = actual
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
            actual = actual
        )
        runAsync.retrunResult()
        assertEquals(
            expected = expected,
            actual = actual
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
