package com.example.garmentlifecycletrackingapp

interface GarmentRepository {
    fun getGarments(): List<Garment>
}
