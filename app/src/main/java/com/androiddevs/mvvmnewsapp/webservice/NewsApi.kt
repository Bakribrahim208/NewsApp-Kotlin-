package com.androiddevs.mvvmnewsapp.webservice

import com.androiddevs.mvvmnewsapp.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.API_KEY
import retrofit2.Response

interface NewsApi {


    @GET("v2/top-headlines")
    suspend fun GetAllNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
            ;


    @GET("v2/everything")
    suspend fun GetSearchNews(
        @Query("q")
        searchQuay: String ,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
            ;
}