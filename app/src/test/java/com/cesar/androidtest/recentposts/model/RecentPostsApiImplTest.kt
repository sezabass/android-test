package com.cesar.androidtest.recentposts.model

import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecentPostsApiImplTest {

    private lateinit var api: RecentPostsApiImpl
    @Mock
    private lateinit var mockListener: RecentPostsApi.ResultListener
    @Mock
    private lateinit var mockService: RecentPostsService
    @Mock
    private lateinit var mockListCall: Call<RecentPostModel>
    private val sampleList = arrayOf(RecentPostModel())
    private val sampleRoot = RecentPostModel()
    @Captor
    private lateinit var callbackArgumentCaptor: ArgumentCaptor<Callback<RecentPostModel>>

    private fun initSampleData() {
        val samplePost = RecentPostModel()
        val sampleData = Data()
        sampleData.author = "Sample Author"
        sampleData.title = "Sample Title"
        sampleData.url = "http://www.pudim.com.br"
        samplePost.data = sampleData
        sampleList[0] = samplePost
        val sampleRootData = Data()
        sampleRootData.children = sampleList
        sampleRoot.data = sampleRootData
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        initSampleData()
        api = RecentPostsApiImpl(mockService)
    }

    @Test
    fun whenApiListThenCallServiceList() {
        api.list(mockListener)
        verify(mockService).list()
    }

    @Test
    fun givenApiListResponseWhenHasGoodDataThenCallbackResponseSuccessful() {
        val response: Response<RecentPostModel> =
                Response.success(sampleRoot)

        `when`(mockService.list()).thenReturn(mockListCall)
        api.list(mockListener)
        verify(mockListCall).enqueue(callbackArgumentCaptor.capture())
        callbackArgumentCaptor.value.onResponse(mockListCall, response)

        verify(mockListener).onResponseSuccessful(ArgumentMatchers.anyList())
    }

    @Test
    fun givenApiListResponseWhenHasWrongDataThenCallbackResponseNotSuccessful() {
        val response: Response<RecentPostModel> =
                Response.success(null)

        `when`(mockService.list()).thenReturn(mockListCall)
        api.list(mockListener)
        verify(mockListCall).enqueue(callbackArgumentCaptor.capture())
        callbackArgumentCaptor.value.onResponse(mockListCall, response)

        verify(mockListener).onResponseNotSuccessful()
    }

    @Test
    fun givenApiListResponseWhenErrorThenCallbackResponseNotSuccessful() {
        val response: Response<RecentPostModel> =
                Response.error(404, ResponseBody.create(
                        MediaType.parse("application/json"),
                        "\"{\\\"sampleKey\\\":[\\\"sampleValue\\\"]}\"")
                )

        `when`(mockService.list()).thenReturn(mockListCall)
        api.list(mockListener)
        verify(mockListCall).enqueue(callbackArgumentCaptor.capture())
        callbackArgumentCaptor.value.onResponse(mockListCall, response)

        verify(mockListener).onResponseNotSuccessful()
    }

    @Test
    fun whenApiListFailureThenCallbackFailure() {
        val message = "Some error message"
        `when`(mockService.list()).thenReturn(mockListCall)
        api.list(mockListener)
        verify(mockListCall).enqueue(callbackArgumentCaptor.capture())
        callbackArgumentCaptor.value.onFailure(mockListCall, Throwable(message))

        verify(mockListener).onFailure(message)
    }
}