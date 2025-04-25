package com.example.myhellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myhellocompose.data.AppDestinations
import com.example.myhellocompose.ui.theme.MyHelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyHelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokemonApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PokemonApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.POKEMON_LIST_ROUTE
    ) {
        composable(AppDestinations.POKEMON_LIST_ROUTE) {
            PokemonListScreen(
                onPokemonClick = { name ->
                    navController.navigate("${AppDestinations.POKEMON_DETAIL_ROUTE}/$name")
                }
            )
        }

        composable(
            route = AppDestinations.POKEMON_DETAIL_ROUTE_WITH_ARGS,
            arguments = listOf(navArgument(AppDestinations.POKEMON_NAME_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString(AppDestinations.POKEMON_NAME_KEY)
            PokemonDetailScreen(pokemonName = name ?: "不明")
        }
    }
}

@Composable
fun PokemonListScreen(onPokemonClick: (String) -> Unit) {
    val pokemons = listOf("Pikachu", "Charmander", "Squirtle")

    Column {
        Text(text = "ポケモン一覧", style = MaterialTheme.typography.headlineMedium)
        pokemons.forEach { name ->
            Button(onClick = { onPokemonClick(name) }) {
                Text(text = name)
            }
        }
    }
}

@Composable
fun PokemonDetailScreen(pokemonName: String) {
    Column {
        Text(text = "ポケモン詳細", style = MaterialTheme.typography.headlineMedium)
        Text(text = "選ばれたポケモン: $pokemonName", style = MaterialTheme.typography.bodyLarge)
    }
}
