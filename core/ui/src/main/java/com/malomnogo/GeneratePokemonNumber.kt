package com.malomnogo

interface GeneratePokemonNumber {
    fun generateNumber(id: Int): String

    class Base : GeneratePokemonNumber {
        override fun generateNumber(id: Int): String = if (id in 0..999) "#%03d".format(id) else "#unknown"
    }
}
