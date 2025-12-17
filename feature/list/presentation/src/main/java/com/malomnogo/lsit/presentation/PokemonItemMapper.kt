package com.malomnogo.lsit.presentation

import com.malomnogo.GeneratePokemonImageUrl
import com.malomnogo.common.Mapper
import com.malomnogo.domain.PokemonDomain

class PokemonItemMapper(
    private val generateImage: GeneratePokemonImageUrl,
) : Mapper<PokemonDomain, PokemonUiItem> {
    
    override fun map(input: PokemonDomain): PokemonUiItem =
        with(input) {
            PokemonUiItem(
                id = id,
                name = name,
                imageUrl = generateImage.generateUrl(id),
            )
        }
}
