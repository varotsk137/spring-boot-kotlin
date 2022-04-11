package com.example.kotlin.adaptor.impl

import com.example.kotlin.adaptor.BaseAdaptor
import com.example.kotlin.model.dto.PokemonSpecies
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

@Configuration
class PokemonAdaptor(@Autowired private val restTemplate: RestTemplate) : BaseAdaptor() {
    override fun getRestTemplate(): RestTemplate = restTemplate

    @Cacheable(key = "#p0", cacheNames = ["pokemon"], unless = "#result == null")
    fun retrievePokemonSpecieFromUrl(url: String): PokemonSpecies {
        val response: ResponseEntity<PokemonSpecies> = super.get(url, PokemonSpecies::class.java)
        return response.body ?: throw NoSuchElementException("Doesn't found data on the specify url")
    }
}