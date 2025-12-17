package com.malomnogo.lsit.presentation

import com.malomnogo.GeneratePokemonImageUrl

internal class FakeGenerateImageUrl : GeneratePokemonImageUrl {
    override fun generateUrl(id: Int) = "https://$id.jpg"
}
