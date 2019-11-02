package com.android.mohamed_habib.productlist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.mohamed_habib.base.BaseViewModel
import com.android.mohamed_habib.data.ProductDataSource
import com.android.mohamed_habib.data.dto.APIResult
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class ProductListViewModel(
    app: Application,
    private val repo: ProductDataSource
) : BaseViewModel(app) {
    val productsList = MutableLiveData<List<DataItem>>()

    init {
        loadProducts()
    }

    fun loadProducts() {
        showLoading.value = true
        viewModelScope.launch {
            val result = repo.getProducts()
            showLoading.postValue(false)
            when (result) {
                is APIResult.Success -> {
                    val dataList = ArrayList<DataItem>()
                    result.data.forEach { category ->
                        dataList.add(DataItem.HeaderViewItem(category.name))

                        dataList.addAll(category.products.map { product ->
                            DataItem.ProductViewItem(product)
                        })
                    }

                    productsList.value = dataList
                }

                is APIResult.Error ->
                    showSnackBar.value = (result.errorBody as ResponseBody).string()
            }
            //check if no data has to be shown
            invalidateShowNoData()
        }
    }

    fun invalidateShowNoData() {
        showNoData.value =
            productsList.value == null || productsList.value!!.isEmpty()
    }
}