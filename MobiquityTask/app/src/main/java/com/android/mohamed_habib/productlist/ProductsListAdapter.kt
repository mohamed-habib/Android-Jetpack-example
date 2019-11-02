package com.android.mohamed_habib.productlist

import com.android.mohamed_habib.base.BaseRecyclerViewAdapter
import com.android.mohamed_habib.ui.R
import java.lang.IllegalArgumentException


class ProductsListAdapter(callBack: (selectedProduct: DataItem) -> Unit) :
    BaseRecyclerViewAdapter<DataItem>(callBack) {
    companion object {
        val ITEM_VIEW_TYPE_HEADER = 0
        val ITEM_VIEW_TYPE_PRODUCT = 1

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.HeaderViewItem -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ProductViewItem -> ITEM_VIEW_TYPE_PRODUCT
        }
    }

    override fun getLayoutRes(viewType: Int): Int {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> R.layout.it_category
            ITEM_VIEW_TYPE_PRODUCT -> R.layout.it_product
            else -> throw IllegalArgumentException("Unknown viewType $viewType")
        }
    }

}