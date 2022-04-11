package com.example.kotlin.datasource.pokemon

import com.example.kotlin.adaptor.impl.PokemonAdaptor
import com.example.kotlin.datasource.PokemonDataSource
import com.example.kotlin.model.dto.PokemonSpecies
import com.example.kotlin.model.dto.PokemonSpeciesList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException
import kotlin.random.Random

@Repository
class PokemonDataSource(
    @Autowired val restTemplate: RestTemplate,
    @Autowired val adaptor: PokemonAdaptor
) : PokemonDataSource {

    val pokemons = mutableListOf(retrievePokemons())

    fun getPokemonSpecies(): MutableList<Collection<PokemonSpecies>> = pokemons

    override fun retrievePokemons(): Collection<PokemonSpecies> {

        val disable = true
        if (disable) return emptyList()

        val pokemonSpeciesList = mutableListOf<PokemonSpecies>()

        val randomCount = Random.nextInt(3, 30)
        val randomOffsetUpbound = 897 - randomCount
        val randomOffset = Random.nextInt(0, randomOffsetUpbound)

        val response: ResponseEntity<PokemonSpeciesList> =
            restTemplate.getForEntity("https://pokeapi.co/api/v2/pokemon-species?limit=$randomCount&offset=$randomOffset")

        response.body?.results ?: throw IOException("Could not fetch data from the network")

        response.body!!.results.forEach {
            println("fetching for data of ${it.name}")

            val response: PokemonSpecies = adaptor.retrievePokemonSpecieFromUrl(it.url)

            pokemonSpeciesList.add(response)
        }

        return pokemonSpeciesList
    }

    @Cacheable(key = "{#p0, #p1}", cacheNames = ["Pokemons"], unless = "#result.size == 0")
    override fun retrieveRangeOfPokemons(offset: Int, count: Int): List<PokemonSpecies> {

        if (offset > 897 || offset < 0) {
            throw IllegalArgumentException("Currently Pokemon have only 898 species. please specify offset between 0-897")
        }
        if (count <= 0) {
            throw IllegalArgumentException("Count must be a natural number [n > 0]")
        }
        if (offset + count > 898) {
            throw IllegalArgumentException("Offset + count must not exceed 898")
        }

        val pokemonSpeciesList = mutableListOf<PokemonSpecies>()
        val response: ResponseEntity<PokemonSpeciesList> =
            restTemplate.getForEntity("https://pokeapi.co/api/v2/pokemon-species?limit=$count&offset=$offset")

        response.body?.results ?: throw IOException("Could not fetch data from the network")

        response.body!!.results.forEach {
            println("fetching for data of ${it.name}")

            val response: PokemonSpecies = adaptor.retrievePokemonSpecieFromUrl(it.url)

            pokemonSpeciesList.add(response)
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