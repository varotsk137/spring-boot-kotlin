package com.example.kotlin.datasource.pokemon

import com.example.kotlin.datasource.PokemonDataSource
import com.example.kotlin.model.dto.PokemonSpecies
import com.example.kotlin.model.dto.PokemonSpeciesList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository
class PokemonDataSource(
    @Autowired val restTemplate: RestTemplate
): PokemonDataSource {

    val pokemons = mutableListOf(retrievePokemons())

    fun getPokemonSpecies(): MutableList<Collection<PokemonSpecies>> = pokemons

    override fun retrievePokemons(): Collection<PokemonSpecies> {

        val pokemonSpeciesList = mutableListOf<PokemonSpecies>()

        val response: ResponseEntity<PokemonSpeciesList> =
            restTemplate.getForEntity("https://pokeapi.co/api/v2/pokemon-species?limit=30&offset=654")

         response.body?.results ?: throw IOException("Could not fetch data from the network")

        response.body!!.results.forEach {
            println("fetching for data of ${it.name}")

            val response: ResponseEntity<PokemonSpecies> =
                restTemplate.getForEntity(it.url)

            response.body?.let { it1 -> pokemonSpeciesList.add(it1) }
        }

        return pokemonSpeciesList
    }

    override fun retrievePokemon(id: String): PokemonSpecies {
        TODO("Not yet implemented")
    }

    override fun createPokemon(pokemon: PokemonSpecies): PokemonSpecies {
        TODO("Not yet implemented")
    }

    override fun updatePokemon(pokemon: PokemonSpecies): PokemonSpecies {
        TODO("Not yet implemented")
    }

    override fun deletePokemon(id: String) {
        TODO("Not yet implemented")
    }

}