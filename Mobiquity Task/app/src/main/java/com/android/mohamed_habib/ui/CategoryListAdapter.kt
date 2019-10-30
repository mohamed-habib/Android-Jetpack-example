package com.android.mohamed_habib.ui

import com.android.mohamed_habib.network.responses.Category
import com.android.mohamed_habib.network.responses.Product
import com.android.mohamed_habib.ui.base.BaseRecyclerViewAdapter
import com.android.mohamed_habib.ui.base.DataBindingViewHolder
import com.android.mohamed_habib.utils.setup
import kotlinx.android.synthetic.main.it_category.view.*

class CategoryListAdapter(val callback: (selectedProduct: Product) -> Unit) :
    BaseRecyclerViewAdapter<Category>() {
    override fun getLayoutRes() = R.layout.it_category

    override fun onBindViewHolder(holder: DataBindingViewHolder<Category>, position: Int) {
        super.onBindViewHolder(holder, position)
        val adapter = ProductsListAdapter { selectedProduct ->
            callback.invoke(selectedProduct)
        }
        val category = getItem(position)
        adapter.addData(category.products)
        holder.itemView.productsRecyclerView.setup(adapter, 2)
        holder.itemView.productsRecyclerView.setHasFixedSize(true)
    }


}