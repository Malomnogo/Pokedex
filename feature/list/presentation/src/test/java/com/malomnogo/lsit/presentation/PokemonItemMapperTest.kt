package com.malomnogo.lsit.presentation

import com.malomnogo.domain.PokemonDomain
import org.junit.Assert.assertEquals
import org.junit.Test

internal class PokemonItemMapperTest {
    private val generateImage = FakeGenerateImageUrl()
    private val generateNumber = FakeGenerateNumber()
    private val mapper = PokemonItemMapper.Base(generateImage, generateNumber)

    @Test
    fun `map domain model to ui model correctly`() {
        val domainModel =
            PokemonDomain(
                id = 25,
                name = "Pikachu",
            )

        val uiModel = mapper.map(domainModel)

        val expected =
            PokemonUiItem(
                id = 25,
                name = "Pikachu",
                number = "25",
                imageUrl = "https://25.jpg",
            )
        assertEquals(expected, uiModel)
    }
}
