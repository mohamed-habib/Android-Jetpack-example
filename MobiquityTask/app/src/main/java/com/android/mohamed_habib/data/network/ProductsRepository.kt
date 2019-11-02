package com.android.mohamed_habib.data.network

import com.android.mohamed_habib.data.ProductDataSource
import com.android.mohamed_habib.data.dto.APIResult
import com.android.mohamed_habib.data.dto.Category

class ProductsRepository(val api: ProductsService) : BaseRepository(),
    ProductDataSource {

    override suspend fun getProducts(): APIResult<List<Category>> =
        getAPIResult(safeApiCall { api.getProducts() })
}
