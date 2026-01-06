package com.malomnogo

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class GeneratePokemonNumberTest(
    private val inputId: Int,
    private val expectedNumber: String,
) {
    private val generator = GeneratePokemonNumber.Base()

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "id={0}, expected={1}")
        fun data(): List<Array<Any>> =
            listOf(
                arrayOf(1, "#001"),
                arrayOf(10, "#010"),
                arrayOf(25, "#025"),
                arrayOf(100, "#100"),
                arrayOf(999, "#999"),
                arrayOf(1000, "#unknown"),
                arrayOf(10000, "#unknown"),
                arrayOf(1000000, "#unknown"),
            )
    }

    @Test
    fun `generate correct formatted number`() {
        val actual = generator.generateNumber(inputId)
        assertEquals(expectedNumber, actual)
    }
}
