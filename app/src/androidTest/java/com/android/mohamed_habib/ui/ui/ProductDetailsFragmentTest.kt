package com.android.mohamed_habib.ui.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.mohamed_habib.network.responses.Product
import com.android.mohamed_habib.network.responses.SalePrice
import com.android.mohamed_habib.ui.ProductDetailsFragment
import com.android.mohamed_habib.ui.ProductDetailsFragmentArgs
import com.android.mohamed_habib.ui.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class ProductDetailsFragmentTest {

    @Test
    fun productDetails_DisplayedOnUI() {
        val selectedProduct =
            Product(1, 12, "Test Product", "/Bread.jpg", "desc", SalePrice(21.9, "EUR"))

        val bundle = ProductDetailsFragmentArgs(selectedProduct).toBundle()
        launchFragmentInContainer<ProductDetailsFragment>(
            bundle,
            R.style.AppTheme
        )

        onView(withId(R.id.ivBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.productName)).check(matches(isDisplayed()))
        onView(withId(R.id.productName)).check(matches(withText(selectedProduct.name)))
        onView(withId(R.id.productPrice)).check(matches(isDisplayed()))
        onView(withId(R.id.productPrice)).check(matches(withText(selectedProduct.price)))
        onView(withId(R.id.productDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.productDescription)).check(matches(withText(selectedProduct.description)))

        Thread.sleep(3000)
    }

    @Test
    fun productDetails_Hidden_OnNoData() {
        //the tablet case no args will be sent to the fragment, so i need to validate that no data is shown.
        launchFragmentInContainer<ProductDetailsFragment>(
            null,
            R.style.AppTheme
        )
        onView(withId(R.id.rootDetailsView)).check(matches(withEffectiveVisibility(Visibility.GONE)))

    }

}