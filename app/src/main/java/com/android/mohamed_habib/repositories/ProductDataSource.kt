package com.android.mohamed_habib.repositories

import com.android.mohamed_habib.network.responses.APIResult
import com.android.mohamed_habib.network.responses.Category

interface ProductDataSource {
    suspend fun getProducts(): APIResult<List<Category>>
}