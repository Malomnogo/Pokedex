package com.malomnogo

import javax.inject.Inject

interface GeneratePokemonNumber {
    fun generateNumber(id: Int): String

    class Base @Inject constructor() : GeneratePokemonNumber {
        override fun generateNumber(id: Int): String = if (id in 0..999) "#%03d".format(id) else "#unknown"
    }
}
