package com.example.myhellocompose.data

object AppDestinations {
	const val POKEMON_LIST_ROUTE = "pokemonList"
	const val POKEMON_DETAIL_ROUTE = "pokemonDetail"
	const val POKEMON_NAME_KEY = "pokemonName"
	const val POKEMON_DETAIL_ROUTE_WITH_ARGS = "$POKEMON_DETAIL_ROUTE/{$POKEMON_NAME_KEY}"
}
