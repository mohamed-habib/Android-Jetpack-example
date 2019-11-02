package com.android.mohamed_habib.data.dto

import com.android.mohamed_habib.data.dto.Product
import com.android.mohamed_habib.utils.TestUtils
import com.android.mohamed_habib.ui.BuildConfig
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductTest {
    private val mockProductJson = "      { " +
            "        \"id\": \"1\", " +
            "        \"categoryId\": \"36802\", " +
            "        \"name\": \"Bread\", " +
            "        \"url\": \"/Bread.jpg\", " +
            "        \"description\": \"\", " +
            "        \"salePrice\": { " +
            "          \"amount\": \"0.81\", " +
            "          \"currency\": \"EUR\" " +
            "        } " +
            "      }"
    lateinit var mockProduct: Product

    @Before
    fun setUp() {
        mockProduct = TestUtils.parseProduct(mockProductJson)
    }

    @Test
    fun getImageUrl_Parsed_Success() {
        val url = "Bread.jpg"
        val expectedImageUrl = BuildConfig.BASE_URL + url
        Assert.assertNotNull(mockProduct)
        Assert.assertThat(expectedImageUrl, `is`(mockProduct.imageUrl))
    }

    @Test
    fun getImageUrl_EmptyUrl() {
        val mockProductJson = "      { " +
                "        \"id\": \"1\", " +
                "        \"categoryId\": \"36802\", " +
                "        \"name\": \"Bread\", " +
                "        \"url\": \"\", " +
                "        \"description\": \"\", " +
                "        \"salePrice\": { " +
                "          \"amount\": \"0.81\", " +
                "          \"currency\": \"EUR\" " +
                "        } " +
                "      }"
        val mockProduct = TestUtils.parseProduct(mockProductJson)
        val expectedImageUrl = ""
        Assert.assertNotNull(mockProduct)
        Assert.assertThat(expectedImageUrl, `is`(mockProduct.imageUrl))
    }

    @Test
    fun getPrice_Parsed_Success() {
        val expectedPrice = "0.81 EUR"
        Assert.assertNotNull(mockProduct)
        Assert.assertNotNull(mockProduct.salePrice)
        Assert.assertNotNull(mockProduct.salePrice.amount)
        Assert.assertNotNull(mockProduct.salePrice.currency)
        Assert.assertThat(expectedPrice, `is`(mockProduct.price))
    }

    @Test
    fun getName_Success() {
        val expectedName = "Bread"
        Assert.assertNotNull(mockProduct)
        Assert.assertNotNull(mockProduct.name)
        Assert.assertThat(expectedName, `is`(mockProduct.name))
    }

}