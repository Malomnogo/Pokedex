package com.malomnogo

interface GeneratePokemonNumber {
    fun generateNumber(id: Int): String

    class Base : GeneratePokemonNumber {
        override fun generateNumber(id: Int): String {
            return "#%03d".format(id)
        }
    }
}
