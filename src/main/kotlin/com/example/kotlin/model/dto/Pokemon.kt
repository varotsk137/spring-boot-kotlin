package com.example.kotlin.model.dto

import java.io.Serializable

data class Pokemon(
    val name: String,
    val url: String,
) : Serializable