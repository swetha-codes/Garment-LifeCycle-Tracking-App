package com.example.garmentlifecycletrackingapp

class FakeGarmentRepository : GarmentRepository {

    override fun getGarments(): List<Garment> {
        return listOf(
            Garment("Blue Shirt", 5, 1, "Washed"),
            Garment("Black Jeans", 5, 2, "Worn"),
            Garment("Red Dress", 1, 0, "Stored"),
            Garment("Grey Hoodie", 4, 1, "Worn"),
            Garment("Green Jacket", 2, 1, "Stored"),
            Garment("White T‑Shirt", 6, 3, "Worn"),
            Garment("Yellow Skirt", 2, 0, "Stored"),
            Garment("Blue Jeans", 7, 3, "Worn"),
            Garment("Beige Coat", 1, 0, "Stored"),
            Garment("Sport Leggings", 4, 2, "Washed")
        )
    }
}
