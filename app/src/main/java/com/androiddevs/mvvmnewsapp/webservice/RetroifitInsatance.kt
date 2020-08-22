package com.androiddevs.mvvmnewsapp.webservice

import com.androiddevs.mvvmnewsapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

class RetroifitInsatance {



  companion object{
      public fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
          val loggingInterceptor = HttpLoggingInterceptor()
          loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
          OkHttpClient.Builder()
              .addInterceptor(loggingInterceptor)
              .build()
      } else OkHttpClient
          .Builder()
          .build()

      public    fun provideRetrofit(
          okHttpClient: OkHttpClient,
          BASE_URL: String
      ): Retrofit =
          Retrofit.Builder()
              .addConverterFactory(GsonConverterFactory.create())
              .baseUrl(BASE_URL)
              .client(okHttpClient)
              .build()


      private fun provideApiService(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)



      private  val retrofit by lazy{
          val loggin=HttpLoggingInterceptor()
          loggin.setLevel(HttpLoggingInterceptor.Level.BODY)
//          val client=OkHttpClient.Builder().addInterceptor(loggin).build()





          Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(provideOkHttpClient()).build()

      }

      val api by lazy{
          retrofit.create(NewsApi::class.java)
      }
  }

}