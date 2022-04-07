package com.example.kotlin.service

import com.example.kotlin.datasource.pokemon.PokemonDataSource
import com.example.kotlin.model.dto.PokemonSpecies
import org.springframework.stereotype.Service

@Service
class PokemonService(private val dataSource: PokemonDataSource) {

    fun getPokemons(): MutableList<Collection<PokemonSpecies>> = dataSource.getPokemonSpecies()
    fun getPokemon(id: String): PokemonSpecies = dataSource.retrievePokemon(id)
    fun addPokemon(pokemon: PokemonSpecies): PokemonSpecies = dataSource.createPokemon(pokemon)
    fun updatePokemon(pokemon: PokemonSpecies): PokemonSpecies = dataSource.updatePokemon(pokemon)
    fun deletePokemon(id: String): Unit = dataSource.deletePokemon(id)

}