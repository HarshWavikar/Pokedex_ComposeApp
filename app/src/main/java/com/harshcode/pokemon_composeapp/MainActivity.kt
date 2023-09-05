package com.harshcode.pokemon_composeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.harshcode.pokemon_composeapp.pokemonDetailScreen.PokemonDetailScreen
import com.harshcode.pokemon_composeapp.pokemonListScreen.PokemonListScreen
import com.harshcode.pokemon_composeapp.ui.theme.Pokemon_ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pokemon_ComposeAppTheme {
            val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pokemon_list_screen"
                ){
                    composable("pokemon_list_screen"){
                        PokemonListScreen(navController = navController)
                    }

                    composable(
                        "pokemon_detail_screen/{dominantColor}/{pokemonName}",
                        arguments = listOf(
                             navArgument("dominantColor"){
                                 type = NavType.IntType
                             },
                            navArgument("pokemonName"){
                                type = NavType.StringType
                            }

                        )
                        ){
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            //the color is just Int,and not in the form of compose color, we need to wrap that into such a color object
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }

                        PokemonDetailScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonName!!.toLowerCase(Locale.ROOT),
                            navController = navController)
                    }
                }
            }
        }
    }
}
