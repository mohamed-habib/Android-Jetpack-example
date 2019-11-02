package com.android.mohamed_habib.productlist

import com.android.mohamed_habib.data.dto.Product
import com.android.mohamed_habib.ui.R
import com.android.mohamed_habib.base.BaseRecyclerViewAdapter


class ProductsListAdapter(callBack: (selectedProduct: Product) -> Unit) :
    BaseRecyclerViewAdapter<Product>(callBack) {
    override fun getLayoutRes() = R.layout.it_product


}