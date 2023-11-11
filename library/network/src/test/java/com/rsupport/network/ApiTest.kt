package com.rsupport.network

import com.google.gson.GsonBuilder
import com.rsupport.network.api.WeatherService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class ApiTest {
    private val job = Job()
    private val dispatcher = StandardTestDispatcher()
    private val testScope = TestScope(dispatcher + job)
    private lateinit var weatherService: WeatherService

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        val retrofit = Retrofit.Builder()
            .client(builder.build())
            .baseUrl("https://apis.data.go.kr")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        weatherService = retrofit.create(WeatherService::class.java)
    }

    @After
    fun tearDown() {
        //test4
        Dispatchers.resetMain()
    }

    @Test
    fun `날씨_데이터를_불러온다`() = testScope.runTest {
        val currentDate = LocalDate.now()
        val oneHourAgo = LocalTime.now().minusMinutes(60)
        val formattedTime = oneHourAgo.format(DateTimeFormatter.ofPattern("HHmm"))
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        val response = weatherService.getWeather(
            API_KEY,
            1,
            10,
            "JSON",
            formattedDate,
            formattedTime,
            59,
            126
        )
        assert(response.isSuccessful)
        assert(response.body()!!.response.body.items.item.isNotEmpty())

    }

}