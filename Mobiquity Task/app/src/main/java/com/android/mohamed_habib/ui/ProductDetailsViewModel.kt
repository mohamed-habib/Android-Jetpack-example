package com.android.mohamed_habib.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.mohamed_habib.network.responses.Product
import com.android.mohamed_habib.ui.base.BaseViewModel

class ProductDetailsViewModel(app: Application) : BaseViewModel(app) {
    val selectedProduct = MutableLiveData<Product>()
    val rootVisibility = MutableLiveData<Boolean>()

}