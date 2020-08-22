package com.androiddevs.mvvmnewsapp.di.modual

import org.koin.dsl.module
import com.androiddevs.mvvmnewsapp.webservice.RetroifitInsatance.Companion.provideOkHttpClient
import com.androiddevs.mvvmnewsapp.webservice.RetroifitInsatance.Companion.provideRetrofit
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.BASE_URL

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(),  BASE_URL) }

}