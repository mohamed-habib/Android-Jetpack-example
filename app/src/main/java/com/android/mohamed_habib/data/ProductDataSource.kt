package com.android.mohamed_habib.data

import com.android.mohamed_habib.data.dto.APIResult
import com.android.mohamed_habib.data.dto.Category

interface ProductDataSource {
    suspend fun getProducts(): APIResult<List<Category>>
}