package com.androiddevs.mvvmnewsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.model.Article
import com.androiddevs.mvvmnewsapp.model.NewsResponse
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    var newsRespoistay: NewsRespoistay
) : ViewModel() {
    val breakingNes: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    val searchNewsResposne: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchbreakingNewsPage = 1
    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNes.postValue(Resource.Loading())
        val response = newsRespoistay.getBreakingNews(countryCode, breakingNewsPage)
        breakingNes.postValue(handleBreakingNewsResposne(response))
    }

    fun searchNews(quary: String) = viewModelScope.launch {
        searchNewsResposne.postValue(Resource.Loading())
        val response = newsRespoistay.searchNesws(quary, searchbreakingNewsPage)
        searchNewsResposne.postValue(handleSearchResposne(response))
    }

    private fun handleBreakingNewsResposne(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { retsultResponse ->
                return Resource.Success(retsultResponse)

            }


        }

        return Resource.Error(response.message())
    }

    private fun handleSearchResposne(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { retsultResponse ->
                return Resource.Success(retsultResponse)

            }


        }

        return Resource.Error(response.message())
    }



    fun saveArtical(article: Article)=viewModelScope.launch {

        newsRespoistay.insert(article)
    }

    fun deleteArtical(article: Article)=viewModelScope.launch {

        newsRespoistay.deleteArtical(article)
    }



    fun getFavouriteArticals()= newsRespoistay.getSavedArtical()
}