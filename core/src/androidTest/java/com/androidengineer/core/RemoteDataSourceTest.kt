package com.androidengineer.core

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidengineer.core.data.remote.RemoteDataSource
import com.androidengineer.core.data.remote.api.ApiService
import com.androidengineer.core.data.remote.model.Response
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class RemoteDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
        remoteDataSource = RemoteDataSource(apiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getWeatherData_returns_success_when_API_responds_correctly() = runTest {
        val jsonResponse = """
            {
              "location": {
                "name": "Lisbon",
                "country": "Portugal",
                "lat": 38.72,
                "lon": -9.13
              },
              "forecast": { "forecastday": [] }
            }
        """.trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setBody(jsonResponse)
                .setResponseCode(200)
        )

        val result = remoteDataSource.getWeatherData("5a5bf784f73546f48cb225751252706", 38.72, -9.13, 3)

        assertThat(result is Response.Success).isTrue()
        val data = (result as Response.Success).data
        assertThat(data.location.name).isEqualTo("Lisbon")
        assertThat(data.location.country).isEqualTo("Portugal")
    }

    @Test
    fun getWeatherData_returns_error_on_failure() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        val result = remoteDataSource.getWeatherData("5a5bf784f73546f48cb225751252706", 0.0, 0.0, 3)

        assertThat(result is Response.Error).isTrue()
    }
}