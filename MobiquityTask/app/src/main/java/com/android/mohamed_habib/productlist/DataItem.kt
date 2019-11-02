package com.android.mohamed_habib.productlist

import com.android.mohamed_habib.data.dto.Product

sealed class DataItem {
    data class ProductViewItem(var product: Product?) : DataItem()
    data class HeaderViewItem(var categoryName: String) : DataItem()
}

