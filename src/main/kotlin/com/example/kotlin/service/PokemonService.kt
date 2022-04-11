package com.example.kotlin.service

import com.example.kotlin.datasource.pokemon.PokemonDataSource
import com.example.kotlin.model.dto.PokemonSpecies
import org.springframework.stereotype.Service

@Service
class PokemonService(private val dataSource: PokemonDataSource) {

    fun getPokemons(): MutableList<Collection<PokemonSpecies>> = dataSource.getPokemonSpecies()

    fun retrieveRangeOfPokemon(offset: Int, count: Int): List<PokemonSpecies> =
        dataSource.retrieveRangeOfPokemons(offset, count)

}