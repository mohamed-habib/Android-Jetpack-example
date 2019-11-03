package com.android.mohamed_habib.data.network

import com.android.mohamed_habib.data.dto.APIResult
import retrofit2.Response

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>
    ) = safeApiResult(call)


    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): APIResult<T> {
        var response: Response<T>? = null
        try {
            response = call.invoke()
        } catch (ex: Exception) {
            ex.printStackTrace()
            return getResultError(response)
        }

        if (response.isSuccessful) return APIResult.Success(response.body()!!)

        return getResultError(response)
    }

    private fun <T> getResultError(response: Response<T>?) =

        APIResult.Error(response?.errorBody(), response?.code())

    protected fun <T : Any> getAPIResult(response: APIResult<T>): APIResult<T> {
        return when (response) {
            is APIResult.Success -> APIResult.Success(response.data)
            is APIResult.Error -> response
        }
    }
}
