package com.android.mohamed_habib.data.dto

import okhttp3.ResponseBody

sealed class APIResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : APIResult<T>()
    data class Error(val errorBody: ResponseBody?, val statusCode: Int? = null) :
        APIResult<Nothing>()
}