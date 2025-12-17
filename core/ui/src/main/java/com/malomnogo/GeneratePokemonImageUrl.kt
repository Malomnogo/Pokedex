package com.malomnogo

interface GeneratePokemonImageUrl {
    fun generateUrl(id: Int): String

    class DreamWorld : GeneratePokemonImageUrl {
        override fun generateUrl(id: Int): String =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg"
    }
}
