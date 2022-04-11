package com.example.kotlin.controller

import com.example.kotlin.model.dto.PokemonSpecies
import com.example.kotlin.service.PokemonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/hello")
class HelloController(private val service: PokemonService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun helloWorld(): String {
        return "Hello, this is a REST endpoint.";
    }

    @GetMapping("/pokemon")
    fun testPkmns(): MutableList<Collection<PokemonSpecies>> {
        return service.getPokemons()
    }

    @GetMapping("/pokemon/range")
    fun testRangeOfPkmns(@RequestParam offset: Int, @RequestParam count: Int): List<PokemonSpecies> {
        return service.retrieveRangeOfPokemon(offset, count)
    }

}