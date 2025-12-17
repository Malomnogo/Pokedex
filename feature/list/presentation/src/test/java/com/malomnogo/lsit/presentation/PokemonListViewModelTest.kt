package com.malomnogo.lsit.presentation

import com.malomnogo.GeneratePokemonImageUrl
import com.malomnogo.domain.PokemonDomain
import com.malomnogo.domain.PokemonListResult
import com.malomnogo.domain.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class PokemonListViewModelTest {
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var repository: FakePokemonListRepository
    private lateinit var generateImageUrl: GeneratePokemonImageUrl
    private lateinit var itemMapper: PokemonItemMapper
    private lateinit var stateMapper: PokemonListStateMapper

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = FakePokemonListRepository()
        generateImageUrl = FakeGenerateImageUrl()
        itemMapper = PokemonItemMapper.Base(generateImageUrl)
        stateMapper = PokemonListStateMapper.Base(itemMapper)
    }

    private fun createViewModel() {
        viewModel =
            PokemonListViewModel(
                repository = repository,
                mapper = stateMapper,
            )
    }

    @Test
    fun `success first time (init)`() =
        runTest {
            repository.returnSuccess()
            createViewModel()

            val actual: StateFlow<PokemonListUiState> = viewModel.uiState
            val expected =
                PokemonListUiState.Base(
                    pokemonList =
                        listOf(
                            PokemonUiItem(
                                id = 1,
                                name = "Bulbasaur",
                                imageUrl = "https://1.jpg",
                            ),
                            PokemonUiItem(
                                id = 4,
                                name = "Charmander",
                                imageUrl = "https://4.jpg",
                            ),
                            PokemonUiItem(
                                id = 7,
                                name = "Squirtle",
                                imageUrl = "https://7.jpg",
                            ),
                        ),
                )

            assertEquals(PokemonListUiState.Progress, actual.value)

            advanceUntilIdle()
            assertEquals(expected, actual.value)
        }

    @Test
    fun `error twice`() =
        runTest {
            repository.returnError()
            createViewModel()

            val actual: StateFlow<PokemonListUiState> = viewModel.uiState
            val expected = PokemonListUiState.Error(message = "No internet connection")

            assertEquals(PokemonListUiState.Progress, actual.value)
            advanceUntilIdle()
            assertEquals(expected, actual.value)

            viewModel.onIntent(PokemonListIntent.Retry)
            assertEquals(PokemonListUiState.Progress, actual.value)
            advanceUntilIdle()
            assertEquals(expected, actual.value)
        }

    @Test
    fun `success after error`() =
        runTest {
            repository.returnError()
            createViewModel()

            val actual: StateFlow<PokemonListUiState> = viewModel.uiState
            var expected: PokemonListUiState = PokemonListUiState.Error(message = "No internet connection")

            assertEquals(PokemonListUiState.Progress, actual.value)
            advanceUntilIdle()
            assertEquals(expected, actual.value)

            repository.returnSuccess()
            expected =
                PokemonListUiState.Base(
                    pokemonList =
                        listOf(
                            PokemonUiItem(
                                id = 1,
                                name = "Bulbasaur",
                                imageUrl = "https://1.jpg",
                            ),
                            PokemonUiItem(
                                id = 4,
                                name = "Charmander",
                                imageUrl = "https://4.jpg",
                            ),
                            PokemonUiItem(
                                id = 7,
                                name = "Squirtle",
                                imageUrl = "https://7.jpg",
                            ),
                        ),
                )

            viewModel.onIntent(PokemonListIntent.Retry)
            assertEquals(PokemonListUiState.Progress, actual.value)
            advanceUntilIdle()
            assertEquals(expected, actual.value)
        }
}

private class FakePokemonListRepository : PokemonRepository {
    lateinit var result: PokemonListResult

    fun returnSuccess() {
        result =
            PokemonListResult.Success(
                pokemonList =
                    listOf(
                        PokemonDomain(
                            id = 1,
                            name = "Bulbasaur",
                        ),
                        PokemonDomain(
                            id = 4,
                            name = "Charmander",
                        ),
                        PokemonDomain(
                            id = 7,
                            name = "Squirtle",
                        ),
                    ),
            )
    }

    fun returnError() {
        result = PokemonListResult.Error(message = "No internet connection")
    }

    override suspend fun fetchPokemonList(): PokemonListResult = result
}
