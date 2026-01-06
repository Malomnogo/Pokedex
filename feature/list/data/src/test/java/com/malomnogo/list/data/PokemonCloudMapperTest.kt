package com.malomnogo.list.data

import com.malomnogo.domain.PokemonDomain
import com.malomnogo.model.PokemonCloud
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonCloudMapperTest {

    private val mapper = PokemonCloudMapper.ToDomain

    @Test
    fun `map cloud model to domain model correctly`() {
        val cloudModel = PokemonCloud(
            name = "pikachu",
            url = "https://pokeapi.co/api/v2/pokemon/25/"
        )

        val domainModel = mapper.map(cloudModel)

        val expected = PokemonDomain(
            id = 25,
            name = "Pikachu"
        )
        assertEquals(expected, domainModel)
    }
}
