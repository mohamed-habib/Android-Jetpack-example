package com.android.mohamed_habib.repositories

import com.android.mohamed_habib.network.ProductsService
import com.android.mohamed_habib.network.responses.APIResult
import com.android.mohamed_habib.network.responses.Category

class ProductsRepository(val api: ProductsService) : BaseRepository(), ProductDataSource {

    override suspend fun getProducts(): APIResult<List<Category>> =
        getAPIResult(safeApiCall { api.getProducts() })
}
