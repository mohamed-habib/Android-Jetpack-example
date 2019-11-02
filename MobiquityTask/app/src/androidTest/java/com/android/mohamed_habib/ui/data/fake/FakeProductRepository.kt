package com.android.mohamed_habib.ui.data.fake

import com.android.mohamed_habib.data.ProductDataSource
import com.android.mohamed_habib.data.dto.APIResult
import com.android.mohamed_habib.data.dto.Category

class FakeProductRepository(val testData: List<Category>) :
    ProductDataSource {

    override suspend fun getProducts(): APIResult<List<Category>> {
        return APIResult.Success(testData)
    }

}