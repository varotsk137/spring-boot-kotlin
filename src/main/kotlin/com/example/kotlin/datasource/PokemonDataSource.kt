package com.example.kotlin.datasource

import com.example.kotlin.model.dto.PokemonSpecies

interface PokemonDataSource {

    fun retrievePokemons(): Collection<PokemonSpecies>
    fun retrieveRangeOfPokemons(offset: Int, count: Int): List<PokemonSpecies>
    fun retrievePokemon(id: String): PokemonSpecies
    fun createPokemon(pokemon: PokemonSpecies): PokemonSpecies
    fun updatePokemon(pokemon: PokemonSpecies): PokemonSpecies
    fun deletePokemon(id: String)

}