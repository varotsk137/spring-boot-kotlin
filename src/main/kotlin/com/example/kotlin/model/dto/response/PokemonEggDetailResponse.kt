package com.example.kotlin.model.dto.response

import com.example.kotlin.model.dto.PokemonSpecies.EggGroup

data class PokemonEggDetailResponse(
    val name: String,
    val hatchCounter: Int,
    val eggGroups: Collection<EggGroup>
    )