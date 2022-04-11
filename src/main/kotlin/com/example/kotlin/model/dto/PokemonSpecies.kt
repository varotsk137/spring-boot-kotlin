package com.example.kotlin.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class PokemonSpecies(
    val id: Int,
    val name: String,
    val order: Int,
    val gender_rate: Int,
    val capture_rate: Int,
    val base_happiness: Int,
    val is_baby: Boolean,
    val is_legendary: Boolean,
    val is_mythical: Boolean,
    val hatch_counter: Int,
    val has_gender_differences: Boolean,
    val forms_switchable: Boolean,
    val growth_rate: GrowthRate,
    val egg_groups: Collection<EggGroup>,
    val evolution_chain: EvolutionChain,
    val generation: Generation,
    val names: Collection<PkmnName>,

    ) : Serializable {
    data class GrowthRate(
        val name: String,
        val url: String
    ) : Serializable

    data class EggGroup(
        val name: String,
        val url: String
    ) : Serializable

    data class EvolutionChain(
        val url: String
    ) : Serializable

    data class Generation(
        val name: String,
        val url: String
    ) : Serializable

    data class PkmnName(
        val name: String,
        val language: Language
    ) : Serializable

    data class Language(
        val name: String,
        val url: String
    ) : Serializable
}