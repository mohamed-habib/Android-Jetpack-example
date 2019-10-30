package com.android.mohamed_habib.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SalePrice(
    @SerializedName("amount") val amount: Double,
    @SerializedName("currency") val currency: String
) : Parcelable