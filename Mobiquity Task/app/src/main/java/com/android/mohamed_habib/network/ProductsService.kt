package com.android.mohamed_habib.network

import com.android.mohamed_habib.network.responses.Category
import retrofit2.Response
import retrofit2.http.GET

interface ProductsService {
    @GET(".")
    suspend fun getProducts(): Response<List<Category>>
}