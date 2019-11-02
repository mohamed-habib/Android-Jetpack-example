package com.android.mohamed_habib.productlist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.mohamed_habib.data.dto.APIResult
import com.android.mohamed_habib.data.dto.Category
import com.android.mohamed_habib.base.BaseViewModel
import com.android.mohamed_habib.data.ProductDataSource
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class ProductListViewModel(
    app: Application,
    private val repo: ProductDataSource
) : BaseViewModel(app) {
    val categoriesLiveData = MutableLiveData<List<Category>>()

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
                    categoriesLiveData.value = result.data
                }
                is APIResult.Error -> {
                    showSnackBar.value = (result.errorBody as ResponseBody).string()
                }
            }
            //check if no data has to be shown
            invalidateShowNoData()
        }
    }

    fun invalidateShowNoData() {
        showNoData.value =
            categoriesLiveData.value == null || categoriesLiveData.value!!.isEmpty()
    }
}