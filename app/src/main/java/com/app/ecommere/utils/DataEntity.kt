package com.app.ecommere.utils

import com.app.ecommere.data.source.local.room.entity.ProductEntity

object DataEntity {
    fun populateData(): List<ProductEntity> {
        val products = mutableListOf<ProductEntity>()

        products.add(
            ProductEntity(
                productCode = "MSDP",
                productName = "Mie Sedap",
                productPrice = 3000,
                currency = "Rp",
                discount = 0,
                dimension = "3 cm * 5 cm",
                unit = "PCS",
                imageName = "mie_sedap",
                description = "The best noodle"
            )
        )

        products.add(
            ProductEntity(
                productCode = "DAIA",
                productName = "Deterjen DAIA",
                productPrice = 20000,
                currency = "Rp",
                discount = 10,
                dimension = "8 cm * 5 cm",
                unit = "PCS",
                imageName = "daia",
                description = "The best soap"
            )
        )

        products.add(
            ProductEntity(
                productCode = "SKLN",
                productName = "So Klin Lantai",
                productPrice = 15000,
                currency = "Rp",
                discount = 0,
                dimension = "9 cm * 5 cm",
                unit = "PCS",
                imageName = "soklin",
                description = "The best floor cleaner"
            )
        )

        products.add(
            ProductEntity(
                productCode = "KCPSDP",
                productName = "Kecap Sedap",
                productPrice = 25000,
                currency = "Rp",
                discount = 10,
                dimension = "8 cm * 2 cm",
                unit = "PCS",
                imageName = "kecap_sedap",
                description = "The best soy sauce"
            )
        )

        products.add(
            ProductEntity(
                productCode = "MMLMN",
                productName = "Mama Lemon",
                productPrice = 10000,
                currency = "Rp",
                discount = 5,
                dimension = "6 cm * 5 cm",
                unit = "PCS",
                imageName = "mama_lemon",
                description = "The best dish cleaner"
            )
        )

        products.add(
            ProductEntity(
                productCode = "FLRDNA",
                productName = "Floridina",
                productPrice = 4000,
                currency = "Rp",
                discount = 0,
                dimension = "5 cm * 2 cm",
                unit = "PCS",
                imageName = "floridina",
                description = "The best fruit flavored drink"
            )
        )

        products.add(
            ProductEntity(
                productCode = "SBNGV",
                productName = "Giv",
                productPrice = 25000,
                currency = "Rp",
                discount = 0,
                dimension = "6 cm * 4 cm",
                unit = "PCS",
                imageName = "giv",
                description = "The best liquid soap"
            )
        )

        products.add(
            ProductEntity(
                productCode = "KDM",
                productName = "Kodomo",
                productPrice = 12000,
                currency = "Rp",
                discount = 0,
                dimension = "5 cm * 2 cm",
                unit = "PCS",
                imageName = "kodomo",
                description = "The best children's toothpaste"
            )
        )

        products.add(
            ProductEntity(
                productCode = "NVO",
                productName = "Nuvo",
                productPrice = 15000,
                currency = "Rp",
                discount = 5,
                dimension = "5 cm * 4 cm",
                unit = "PCS",
                imageName = "nuvo",
                description = "The best liquid soap"
            )
        )

        products.add(
            ProductEntity(
                productCode = "JVN",
                productName = "Teh Javana",
                productPrice = 3000,
                currency = "Rp",
                discount = 0,
                dimension = "5 cm * 2 cm",
                unit = "PCS",
                imageName = "teh_javana",
                description = "The best sweet tea"
            )
        )

        return products
    }
}