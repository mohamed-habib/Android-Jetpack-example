package com.android.mohamed_habib.data.network

import com.android.mohamed_habib.data.dto.Category
import retrofit2.Response
import retrofit2.http.GET

interface ProductsService {
    @GET(".")
    suspend fun getProducts(): Response<List<Category>>
}