package com.androiddevs.mvvmnewsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsProviderFectory(
    val newsRespoistay: NewsRespoistay
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return NewsViewModel(newsRespoistay) as T
    }
}