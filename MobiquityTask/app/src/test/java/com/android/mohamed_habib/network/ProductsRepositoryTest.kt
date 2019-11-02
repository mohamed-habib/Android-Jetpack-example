package com.android.mohamed_habib.network

import com.android.mohamed_habib.data.network.ProductsService
import com.android.mohamed_habib.data.network.ProductsRepository
import com.android.mohamed_habib.data.dto.APIResult
import com.android.mohamed_habib.utils.TestUtils
import com.android.mohamed_habib.utils.jsonResponseFileName
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
class ProductsRepositoryTest {
    @get:Rule
    val mockWebServer = MockWebServer()
    val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    lateinit var productsRepository: ProductsRepository

    private val mockRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    public val mockProductsService by lazy {
        mockRetrofit.create(ProductsService::class.java)
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        productsRepository = ProductsRepository(
            mockProductsService
        )
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun getProducts_loadedSuccessfully() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(TestUtils.getJson(jsonResponseFileName))
                .setResponseCode(200)
        )
        runBlocking {
            launch(Dispatchers.Main) {
                when (val result = productsRepository.getProducts()) {
                    is APIResult.Success -> {
                        Assert.assertThat(result.data, `is`(TestUtils.getProductsTestObject()))
                    }
                    is APIResult.Error -> {
                        Assert.fail(result.errorBody.toString())
                    }
                }
            }
        }
    }
}