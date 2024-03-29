package com.android.mohamed_habib.ui.productlist

import android.app.Application
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.mohamed_habib.data.ProductDataSource
import com.android.mohamed_habib.data.dto.Category
import com.android.mohamed_habib.data.dto.Product
import com.android.mohamed_habib.data.dto.SalePrice
import com.android.mohamed_habib.ui.data.fake.FakeProductRepository
import com.android.mohamed_habib.productlist.ProductListFragment
import com.android.mohamed_habib.productlist.ProductListViewModel
import com.android.mohamed_habib.ui.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest


@RunWith(AndroidJUnit4::class)
@MediumTest
class ProductListFragmentTest : AutoCloseKoinTest() {
    lateinit var appContext: Application
    val myModule = module {
        viewModel {
            ProductListViewModel(
                get() as ProductDataSource
            )
        }
        single { FakeProductRepository(
            createCategoriesTestData()
        ) as ProductDataSource }
    }

    val product1 = Product(
        1, 2, "Product1", "/Bread.jpg", "",
        SalePrice(12.0, "USD")
    )

    private fun createCategoriesTestData(): ArrayList<Category> {
        val categoryList = ArrayList<Category>()
        val productsList = ArrayList<Product>()

        productsList.add(
            product1
        )
        productsList.add(
            Product(
                1, 2, "Product2", "/Bread.jpg", "",
                SalePrice(12.0, "USD")
            )
        )
        productsList.add(
            Product(
                1, 2, "Product3", "/Bread.jpg", "",
                SalePrice(12.0, "USD")
            )
        )
        productsList.add(
            Product(
                1, 2, "Product4", "/Bread.jpg", "",
                SalePrice(12.0, "USD")
            )
        )
        productsList.add(
            Product(
                1, 2, "Product5", "/Bread.jpg", "",
                SalePrice(12.0, "USD")
            )
        )

        categoryList.add(
            Category(
                2,
                "Category1",
                "",
                productsList
            )
        )
        categoryList.add(
            Category(
                2,
                "Category1",
                "",
                productsList
            )
        )
        categoryList.add(
            Category(
                2,
                "Category1",
                "",
                productsList
            )
        )
        categoryList.add(
            Category(
                2,
                "Category1",
                "",
                productsList
            )
        )
        return categoryList
    }

    @Before
    fun before() {
        stopKoin()//stop the original app koin
        appContext = ApplicationProvider.getApplicationContext()
        startKoin {
            modules(listOf(myModule))
        }
    }

    @Test
    fun productList_DisplayedOnUI_DataAvailable() {
        launchFragmentInContainer<ProductListFragment>(
            null,
            R.style.AppTheme
        )
        onView(withId(R.id.productsRecyclerView)).check(matches(isDisplayed()))


        onView(withId(R.id.noDataTextView)).check(matches(withEffectiveVisibility(Visibility.GONE)))
//        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}