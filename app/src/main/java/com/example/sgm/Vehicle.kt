package com.example.sgm

import java.util.UUID

data class Vehicle(
    val id: UUID,
    val name: String,
    val model: String,
    val year: String,
    val group: Group?,
    val isActive: Boolean
)
