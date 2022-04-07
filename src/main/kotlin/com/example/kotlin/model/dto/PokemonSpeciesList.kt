package com.example.kotlin.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PokemonSpeciesList(
    val count: Int,
    val results: Collection<Pokemon>
    )