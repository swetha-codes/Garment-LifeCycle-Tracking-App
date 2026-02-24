package com.example.garmentlifecycletrackingapp

data class Garment(
    val name: String,
    val wears: Int,
    val washes: Int,
    val state: String   // e.g. "Worn", "Stored", "Washed"
)
