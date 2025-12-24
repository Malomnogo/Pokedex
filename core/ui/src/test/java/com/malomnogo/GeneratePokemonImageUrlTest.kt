package com.malomnogo

import org.junit.Assert.assertEquals
import org.junit.Test

internal class GeneratePokemonImageUrlTest {

    private val generator = GeneratePokemonImageUrl.DreamWorld()

    @Test
    fun `generate correct url for dream world`() {
        val id = 1
        val expected = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg"

        val actual = generator.generateUrl(id)

        assertEquals(expected, actual)
    }
}
