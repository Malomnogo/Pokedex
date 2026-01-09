package com.malomnogo.lsit.presentation

import com.malomnogo.GeneratePokemonImageUrl
import com.malomnogo.GeneratePokemonNumber
import com.malomnogo.domain.PokemonDomain
import javax.inject.Inject

interface PokemonItemMapper {
    fun map(input: PokemonDomain): PokemonUiItem

    class Base
        @Inject
        constructor(
            private val generateImage: GeneratePokemonImageUrl,
            private val generateNumber: GeneratePokemonNumber,
        ) : PokemonItemMapper {
            override fun map(input: PokemonDomain): PokemonUiItem =
                with(input) {
                    PokemonUiItem(
                        id = id,
                        name = name,
                        imageUrl = generateImage.generateUrl(id),
                        number = generateNumber.generateNumber(id),
                    )
                }
        }
}
