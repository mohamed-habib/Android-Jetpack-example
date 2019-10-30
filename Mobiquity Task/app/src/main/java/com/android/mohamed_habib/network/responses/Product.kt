package com.android.mohamed_habib.network.responses

import android.os.Parcelable
import com.android.mohamed_habib.ui.BuildConfig
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("categoryId") val categoryId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("description") val description: String,
    @SerializedName("salePrice") val salePrice: SalePrice
) : Parcelable {
    val imageUrl
        get() =
            //remove the "/" from the url if not empty
            if (url.isNotEmpty()) BuildConfig.BASE_URL + url.subSequence(1, url.length) else ""

    val price
        get() = "${salePrice.amount} ${salePrice.currency}"
}