package com.malomnogo.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.malomnogo.list.api.PokemonListNavKey
import com.malomnogo.lsit.presentation.navigation.pokemonListEntry
import com.malomnogo.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                val backstack = remember { mutableStateListOf<NavKey>(PokemonListNavKey) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavDisplay(
                        backStack = backstack,
                        entryProvider = { key ->
                            when (key) {
                                is PokemonListNavKey -> pokemonListEntry(
                                    key = key,
                                    onPokemonClick = { pokemonId ->
                                    }
                                )
                                else -> error("Unknown key: $key")
                            }
                        },
                        modifier = Modifier.padding(innerPadding),
                        onBack = {
                            if (backstack.size > 1) {
                                backstack.removeAt(backstack.size - 1)
                            } else {
                                finish()
                            }
                        }
                    )
                }
            }
        }
    }
}
