package com.app.ecommere.utils

import com.app.ecommere.data.source.local.room.entity.ProductEntity

object DataEntity {
    fun populateData(): List<ProductEntity> {
        val products = mutableListOf<ProductEntity>()

        products.add(
            ProductEntity(
                productId = 1,
                productCode = "MSDP",
                productName = "Mie Sedap",
                productPrice = 3000,
                currency = "Rp",
                discount = 0,
                dimension = "3 cm * 5 cm",
                unit = "PCS",
                imageName = "mie_sedap",
                subTitle = "The best noodle",
                description = "Mie Sedaap is one of the leading instant noodle brands made from quality ingredients and natural spices and equipped with the right seasoning formulation. Since it was first launched in 2003, Mie Sedaap has continued to receive positive responses from all instant noodle connoisseurs for the quality of noodles with a chewy texture and delicious taste. Mie Sedaap continues to innovate in providing the best for instant noodle connoisseurs. You can taste 14 flavors of Mie Sedaap which clearly taste delicious and have spread to all corners of Indonesia."
            )
        )

        products.add(
            ProductEntity(
                productId = 2,
                productCode = "DAIA",
                productName = "Deterjen DAIA",
                productPrice = 20000,
                currency = "Rp",
                discount = 10,
                dimension = "8 cm * 5 cm",
                unit = "PCS",
                imageName = "daia",
                subTitle = "The best soap",
                description = "DAIA Detergent with 6 advantages is able to clean clothes all out and keep the colors standing out. With Dual Scent technology, White Booster and Softener divided into 5 variants from DAIA."
            )
        )

        products.add(
            ProductEntity(
                productId = 3,
                productCode = "SKLN",
                productName = "SoKlin Lantai",
                productPrice = 15000,
                currency = "Rp",
                discount = 0,
                dimension = "9 cm * 5 cm",
                unit = "PCS",
                imageName = "soklin",
                subTitle = "The best floor cleaner",
                description = "SoKlin Liquid is a liquid detergent concentrate that cleans clothes effectively, easily and quickly. Enriched with Power Clean Action formula, able to clean spots and keep colors bright without damaging fabric fibers. The anti-bacterial formula makes clothes odorless even when they were soaked for a long time and protects the clothes from the musty smell. Long-lasting perfume gives long-lasting fragrances to your clothes."
            )
        )

        products.add(
            ProductEntity(
                productId = 4,
                productCode = "KCPSDP",
                productName = "Kecap Sedap",
                productPrice = 25000,
                currency = "Rp",
                discount = 10,
                dimension = "8 cm * 2 cm",
                unit = "PCS",
                imageName = "kecap_sedap",
                subTitle = "The best soy sauce",
                description = "Kecap Sedaap is a sweet soy sauce made from selected soybean seeds and other high-quality natural ingredients. Processed with natural fermentation and multifiltration filtering (3 times filtering), produces high quality sweet soy sauce – blacker, tastier, and thicker. This Halal-certified Sedaap Soy Sauce will make your dishes taste unforgettable."
            )
        )

        products.add(
            ProductEntity(
                productId = 5,
                productCode = "MMLMN",
                productName = "Mama Lemon",
                productPrice = 10000,
                currency = "Rp",
                discount = 5,
                dimension = "6 cm * 5 cm",
                unit = "PCS",
                imageName = "mama_lemon",
                subTitle = "The best dish cleaner",
                description = "Bringing MAMA Lemon to the family since 1985, Lion Wings has become a pioneer of dishwashing liquid in Indonesia. The presence of MAMA Lemon has succeeded in making cleaning cutlery and cooking utensils much more practical and hygienic than before."
            )
        )

        products.add(
            ProductEntity(
                productId = 6,
                productCode = "FLRDNA",
                productName = "Floridina",
                productPrice = 4000,
                currency = "Rp",
                discount = 0,
                dimension = "5 cm * 2 cm",
                unit = "PCS",
                imageName = "floridina",
                subTitle = "The best fruit flavored drink",
                description = "Floridina is a refreshing drink made from Florida Oranges. Floridina contains real orange pulp which is extracted using cold filling technology and without the touch of human hands. This orange drink is made without the use of preservatives and is rich in vitamin C. The quality and freshness in every drop of this RTD no.1 juice drink in Indonesia will be very satisfying for consumers."
            )
        )

        products.add(
            ProductEntity(
                productId = 7,
                productCode = "SBNGV",
                productName = "Giv",
                productPrice = 25000,
                currency = "Rp",
                discount = 0,
                dimension = "6 cm * 4 cm",
                unit = "PCS",
                imageName = "giv",
                subTitle = "The best liquid soap",
                description = "In choosing bath soap, the factors that are most in demand by Indonesian women are the need for bath soap that keeps the skin moist and soap with a luxurious and long-lasting fine fragrance. Giv Perfumed Beauty Soap is here to answer the needs and desires of these Indonesian women."
            )
        )

        products.add(
            ProductEntity(
                productId = 8,
                productCode = "KDM",
                productName = "Kodomo",
                productPrice = 12000,
                currency = "Rp",
                discount = 0,
                dimension = "5 cm * 2 cm",
                unit = "PCS",
                imageName = "kodomo",
                subTitle = "The best children's toothpaste",
                description = "Kodomo is one of the children's product brands in Indonesia that has been around for more than 30 years, using animal characters such as elephants, giraffes, and so on. Kodomo consists of 3 product categories that meet the needs of children, namely, dental and oral care, hair care and body care. With Lion Japan's innovation and technology, Kodomo will always be the trusted choice of parents in the children's product category."
            )
        )

        products.add(
            ProductEntity(
                productId = 9,
                productCode = "NVO",
                productName = "Nuvo",
                productPrice = 15000,
                currency = "Rp",
                discount = 5,
                dimension = "5 cm * 4 cm",
                unit = "PCS",
                imageName = "nuvo",
                subTitle = "The best liquid soap",
                description = "NUVO Active is an anti-bacterial health soap combined with skin care ingredients and a more personal perfume."
            )
        )

        products.add(
            ProductEntity(
                productId = 10,
                productCode = "JVN",
                productName = "Teh Javana",
                productPrice = 3000,
                currency = "Rp",
                discount = 0,
                dimension = "5 cm * 2 cm",
                unit = "PCS",
                imageName = "teh_javana",
                subTitle = "The best sweet tea",
                description = "Javana tea is a drink made from original tea that is so refreshing. Starting from fresh tea leaves that are picked and added with real sugar for a fresh taste of brewed tea. Javana tea contains quality tea and real sugar, which is made using aroma lock technology which keeps the aroma and taste fresh like the first brew."
            )
        )

        return products
    }
}