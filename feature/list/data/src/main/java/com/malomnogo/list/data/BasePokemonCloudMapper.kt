package com.malomnogo.list.data

import com.malomnogo.common.Mapper
import com.malomnogo.domain.PokemonDomain
import com.malomnogo.model.PokemonCloud

class BasePokemonCloudMapper : Mapper<PokemonCloud, PokemonDomain> {
    override fun map(input: PokemonCloud): PokemonDomain {
        return PokemonDomain(
            id = input.id,
            name = input.name.replaceFirstChar { it.uppercase() }
        )
    }
}
