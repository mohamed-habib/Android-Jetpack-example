package com.android.mohamed_habib.data.dto

import com.android.mohamed_habib.data.dto.Category
import com.android.mohamed_habib.data.dto.Product
import com.android.mohamed_habib.data.dto.SalePrice
import com.android.mohamed_habib.utils.TestUtils
import org.hamcrest.Matchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoryTest {

    private val mockCategoryJson = "  { " +
            "    \"id\": \"36802\", " +
            "    \"name\": \"Food\", " +
            "    \"description\": \"\", " +
            "    \"products\": [ " +
            "      { " +
            "        \"id\": \"1\", " +
            "        \"categoryId\": \"36802\", " +
            "        \"name\": \"Bread\", " +
            "        \"url\": \"/Bread.jpg\", " +
            "        \"description\": \"\", " +
            "        \"salePrice\": { " +
            "          \"amount\": \"0.81\", " +
            "          \"currency\": \"EUR\" " +
            "        } " +
            "      }, " +
            "      { " +
            "        \"id\": \"2\", " +
            "        \"categoryId\": \"36802\", " +
            "        \"name\": \"Sandwich\", " +
            "        \"url\": \"/Sandwich.jpg\", " +
            "        \"description\": \"\", " +
            "        \"salePrice\": { " +
            "          \"amount\": \"2.01\", " +
            "          \"currency\": \"EUR\" " +
            "        } " +
            "      } " +
            "    ] " +
            "  }"

    lateinit var mockCategory: Category

    @Before
    fun setUp() {
        mockCategory = TestUtils.parseCategory(mockCategoryJson)
    }

    @Test
    fun getName_Success() {
        val expectedName = "Food"
        Assert.assertNotNull(mockCategory)
        Assert.assertNotNull(mockCategory.name)
        Assert.assertNotNull(expectedName, mockCategory.name)
    }

    @Test
    fun getProducts_Success() {
        val expectedProductsList = listOf(
            Product(
                1,
                36802,
                "Bread",
                "/Bread.jpg",
                "",
                SalePrice(0.81, "EUR")
            ),
            Product(
                2,
                36802,
                "Sandwich",
                "/Sandwich.jpg",
                "",
                SalePrice(2.01, "EUR")
            )
        )

        Assert.assertNotNull(mockCategory)
        Assert.assertNotNull(mockCategory.products)
        Assert.assertThat(expectedProductsList, `is`(mockCategory.products))
    }
}