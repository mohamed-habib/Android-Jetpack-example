package com.android.mohamed_habib.ui

import com.android.mohamed_habib.network.responses.Product
import com.android.mohamed_habib.ui.base.BaseRecyclerViewAdapter


class ProductsListAdapter(callBack: (selectedProduct: Product) -> Unit) :
    BaseRecyclerViewAdapter<Product>(callBack) {
    override fun getLayoutRes() = R.layout.it_product


}