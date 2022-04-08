package com.example.kotlin.mapper

import com.example.kotlin.model.dto.response.PokemonEggDetailResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
abstract class PokemonSpeciesMapper {

    @Mapping(target = "name", source = "")
//    @Mapping(target = "hatchCounter", source = "")
//    @Mapping(target = "eggGroups", source = "")
    abstract fun from(pokemonSpeciesMapper: PokemonSpeciesMapper): PokemonEggDetailResponse

}