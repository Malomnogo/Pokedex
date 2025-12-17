package com.malomnogo.lsit.presentation

import com.malomnogo.GeneratePokemonImageUrl
import com.malomnogo.domain.PokemonDomain
import com.malomnogo.domain.PokemonListResult
import com.malomnogo.domain.PokemonRepository
import com.malomnogo.lsit.TestProvideDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class PokemonListViewModelTest {

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var repository: FakePokemonListRepository

    @Before
    fun setup() {
        repository = FakePokemonListRepository()
    }

    @Test
    fun `success first time`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        viewModel = PokemonListViewModel(
            repository = repository,
            mapper = BasePokemonListDomainToUiMapper(
                pokemonMapper = PokemonItemMapper.Base(
                    generateImage = FakeGenerateImageUrl()
                )
            ),
            provideDispatchers = TestProvideDispatcher(testDispatcher)
        )
        repository.returnSuccess()
        val actual: StateFlow<PokemonListUiState> = viewModel.uiState
        val expected = PokemonListUiState.Base(
            pokemonList = listOf(
                PokemonUiItem(
                    id = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://1.jpg"
                ),
                PokemonUiItem(
                    id = 4,
                    name = "Charmander",
                    imageUrl = "https://4.jpg"
                ),
                PokemonUiItem(
                    id = 7,
                    name = "Squirtle",
                    imageUrl = "https://7.jpg"
                )
            )
        )

        viewModel.loadData()
        assertEquals(PokemonListUiState.Progress, actual.value)
        advanceUntilIdle()
        assertEquals(expected, actual.value)
    }

    @Test
    fun `error twice`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        viewModel = PokemonListViewModel(
            repository = repository,
            mapper = BasePokemonListDomainToUiMapper(
                pokemonMapper = PokemonItemMapper.Base(
                    generateImage = FakeGenerateImageUrl()
                )
            ),
            provideDispatchers = TestProvideDispatcher(testDispatcher)
        )
        repository.returnError()
        val actual: StateFlow<PokemonListUiState> = viewModel.uiState
        val expected = PokemonListUiState.Error(message = "No internet connection")
        viewModel.loadData()
        assertEquals(
            PokemonListUiState.Progress,
            actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected,
            actual.value
        )

        viewModel.loadData()
        assertEquals(
            PokemonListUiState.Progress,
            actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected,
            actual.value
        )
    }

    @Test
    fun `success after error`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        viewModel = PokemonListViewModel(
            repository = repository,
            mapper = BasePokemonListDomainToUiMapper(
                pokemonMapper = PokemonItemMapper.Base(
                    generateImage = FakeGenerateImageUrl()
                )
            ),
            provideDispatchers = TestProvideDispatcher(testDispatcher)
        )
        repository.returnError()
        val actual: StateFlow<PokemonListUiState> = viewModel.uiState
        var expected: PokemonListUiState = PokemonListUiState.Error(message = "No internet connection")
        viewModel.loadData()
        assertEquals(
            PokemonListUiState.Progress,
            actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected,
            actual.value
        )

        repository.returnSuccess()
        expected = PokemonListUiState.Base(
            pokemonList = listOf(
                PokemonUiItem(
                    id = 1,
                    name = "Bulbasaur",
                    imageUrl = "https://1.jpg"
                ),
                PokemonUiItem(
                    id = 4,
                    name = "Charmander",
                    imageUrl = "https://4.jpg"
                ),
                PokemonUiItem(
                    id = 7,
                    name = "Squirtle",
                    imageUrl = "https://7.jpg"
                )
            )
        )
        viewModel.loadData()
        assertEquals(
            PokemonListUiState.Progress,
            actual.value
        )
        advanceUntilIdle()
        assertEquals(
            expected,
            actual.value
        )
    }
}

private class FakePokemonListRepository : PokemonRepository {

    lateinit var result: PokemonListResult

    fun returnSuccess() {
        result = PokemonListResult.Success(
            pokemonList = listOf(
                PokemonDomain(
                    id = 1,
                    name = "Bulbasaur"
                ),
                PokemonDomain(
                    id = 4,
                    name = "Charmander"
                ),
                PokemonDomain(
                    id = 7,
                    name = "Squirtle"
                )
            )
        )
    }

    fun returnError() {
        result = PokemonListResult.Error(message = "No internet connection")
    }

    override suspend fun fetchPokemonList(): PokemonListResult {
        return result
    }
}

private class FakeGenerateImageUrl : GeneratePokemonImageUrl {

    override fun generateUrl(id: Int) = "https://$id.jpg"
}
