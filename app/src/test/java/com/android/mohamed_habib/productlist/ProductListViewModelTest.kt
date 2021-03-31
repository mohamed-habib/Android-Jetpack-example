package com.android.mohamed_habib.productlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.mohamed_habib.MainCoroutineRule
import com.android.mohamed_habib.data.network.ProductsRepository
import com.android.mohamed_habib.data.network.ProductsService
import com.android.mohamed_habib.utils.LiveDataTestUtil
import com.android.mohamed_habib.utils.TestUtils
import com.android.mohamed_habib.utils.jsonResponseFileName
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
class ProductListViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockWebServer = MockWebServer()
    private val mockRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val mockProductsService by lazy {
        mockRetrofit.create(ProductsService::class.java)
    }
    val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        stopKoin()
    }

    @Test
    fun loadProducts_Success() {
        val body = TestUtils.getJson(jsonResponseFileName)

        mockWebServer.enqueue(
            MockResponse()
                .setBody(body)
                .setResponseCode(200)
        )

        val productsRepository =
            ProductsRepository(
                mockProductsService
            )
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        val productsViewModel = ProductListViewModel(
            mock(), productsRepository
        )
        //validate loading started
        TestCase.assertEquals(true, LiveDataTestUtil.getValue(productsViewModel.showLoading))

        mainCoroutineRule.resumeDispatcher()

        assertEquals(
            TestUtils.getProductsTestObject().first().name,
            (LiveDataTestUtil.getValue(productsViewModel.productsList)
                .first() as DataItem.HeaderViewItem).categoryName
        )

        TestCase.assertEquals(false, LiveDataTestUtil.getValue(productsViewModel.showLoading))
        TestCase.assertEquals(false, LiveDataTestUtil.getValue(productsViewModel.showNoData))
    }

    @Test
    fun loadProducts_Error() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody("Error")
                .setResponseCode(403)
        )

        val productsRepository =
            ProductsRepository(
                mockProductsService
            )
        // Pause dispatcher so we can verify initial values
        mainCoroutineRule.pauseDispatcher()

        val productsViewModel = ProductListViewModel(
            mock(), productsRepository
        )
        //validate loading started
        TestCase.assertEquals(true, LiveDataTestUtil.getValue(productsViewModel.showLoading))

        mainCoroutineRule.resumeDispatcher()

        TestCase.assertEquals("Error", LiveDataTestUtil.getValue(productsViewModel.showSnackBar))

        TestCase.assertEquals(false, LiveDataTestUtil.getValue(productsViewModel.showLoading))

        TestCase.assertEquals(true, LiveDataTestUtil.getValue(productsViewModel.showNoData))


    }

}