package com.android.mohamed_habib.ui

import com.android.mohamed_habib.network.responses.APIResult
import com.android.mohamed_habib.network.responses.Category
import com.android.mohamed_habib.repositories.ProductDataSource

class FakeProductRepository(val testData: List<Category>) : ProductDataSource {

    override suspend fun getProducts(): APIResult<List<Category>> {
        return APIResult.Success(testData)
    }

}