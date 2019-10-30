package com.android.mohamed_habib

import android.app.Application
import com.android.mohamed_habib.network.API
import com.android.mohamed_habib.repositories.ProductDataSource
import com.android.mohamed_habib.repositories.ProductsRepository
import com.android.mohamed_habib.ui.ProductDetailsViewModel
import com.android.mohamed_habib.ui.ProductListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            viewModel { ProductListViewModel(get(), get() as ProductDataSource) }
            viewModel { ProductDetailsViewModel(get()) }
            single { ProductsRepository(get()) as ProductDataSource }
            single { API.productsApi }
        }
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }

    }
}