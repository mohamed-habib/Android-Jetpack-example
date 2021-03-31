package com.android.mohamed_habib.productdetails

import androidx.lifecycle.MutableLiveData
import com.android.mohamed_habib.data.dto.Product
import com.android.mohamed_habib.base.BaseViewModel

class ProductDetailsViewModel : BaseViewModel() {
    val selectedProduct = MutableLiveData<Product>()
    val rootVisibility = MutableLiveData<Boolean>()

}