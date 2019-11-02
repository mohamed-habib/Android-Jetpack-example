package com.android.mohamed_habib.productdetails

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.mohamed_habib.data.dto.Product
import com.android.mohamed_habib.base.BaseViewModel

class ProductDetailsViewModel(app: Application) : BaseViewModel(app) {
    val selectedProduct = MutableLiveData<Product>()
    val rootVisibility = MutableLiveData<Boolean>()

}