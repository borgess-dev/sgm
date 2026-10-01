package com.example.sgm

import java.util.UUID

data class Group(
    val id: UUID,
    val name: String,
    val description: String,
    val isActive: Boolean
)
