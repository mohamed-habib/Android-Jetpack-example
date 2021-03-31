package com.android.mohamed_habib.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.mohamed_habib.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
    val showErrorMessage: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnackBar: SingleLiveEvent<String> = SingleLiveEvent()
    val showLoading: MutableLiveData<Boolean> = MutableLiveData()
    val showNoData: SingleLiveEvent<Boolean> = SingleLiveEvent()

}