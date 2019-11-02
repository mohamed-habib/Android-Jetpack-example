package com.android.mohamed_habib.utils

import com.android.mohamed_habib.data.dto.Category
import com.android.mohamed_habib.data.dto.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

const val jsonResponseFileName = "products_response.json"

class TestUtils {

    companion object {
        fun getJson(path: String): String {
            val uri = this.javaClass.classLoader!!.getResource(path)
            val file = File(uri.path)
            return String(file.readBytes())
        }

        fun getProductsTestObject(): List<Category> {
            val json =
                getJson(jsonResponseFileName)
            return parseCategoryList(json)
        }

        private fun parseCategoryList(json: String): List<Category> {
            return Gson().fromJson<List<Category>>(
                json, object : TypeToken<List<Category>>() {}.type
            )
        }

        fun parseProduct(productJson: String): Product {
            return Gson().fromJson<Product>(productJson, Product::class.java)
        }
        fun parseCategory(json: String): Category {
            return Gson().fromJson<Category>(json, Category::class.java)
        }

    }
}