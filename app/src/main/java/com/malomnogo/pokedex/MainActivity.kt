package com.malomnogo.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.malomnogo.lsit.presentation.navigation.PokemonListRoute
import com.malomnogo.lsit.presentation.navigation.pokemonListScreen
import com.malomnogo.ui.theme.PokedexTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                PokedexTheme {
                    val navController = rememberNavController()

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = PokemonListRoute,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            pokemonListScreen(
                                onPokemonClick = { pokemonId ->
                                    // TODO: Navigate to details
                                    // navController.navigateToPokemonDetails(pokemonId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
