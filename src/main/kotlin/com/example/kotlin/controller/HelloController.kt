package com.example.kotlin.controller

import com.example.kotlin.model.dto.PokemonSpecies
import com.example.kotlin.service.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class HelloController(private val service: PokemonService) {

    @GetMapping
    fun helloWorld(): String {
        return "Hello, this is a REST endpoint.";
    }

    @GetMapping("/pokemon")
    fun testPkmns(): MutableList<Collection<PokemonSpecies>> {
        return service.getPokemons()
    }
}