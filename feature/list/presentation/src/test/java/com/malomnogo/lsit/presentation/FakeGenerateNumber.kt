package com.malomnogo.lsit.presentation

import com.malomnogo.GeneratePokemonNumber

class FakeGenerateNumber : GeneratePokemonNumber {
    override fun generateNumber(id: Int) = "$id"
}
