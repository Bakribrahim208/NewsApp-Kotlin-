package com.androiddevs.mvvmnewsapp

import androidx.room.RoomDatabase
import com.androiddevs.mvvmnewsapp.db.AritcleDatabase
import com.androiddevs.mvvmnewsapp.model.Article
import com.androiddevs.mvvmnewsapp.webservice.RetroifitInsatance

class NewsRespoistay(
    var articaDatabase: AritcleDatabase
) {

    suspend fun getBreakingNews(codeCountry:String ,PageNumber:Int) =
        RetroifitInsatance.api.GetAllNews(codeCountry,PageNumber)


    suspend fun searchNesws(queary:String ,PageNumber:Int) =
        RetroifitInsatance.api.GetSearchNews(queary,PageNumber)



    suspend fun insert(artical :Article)=articaDatabase.getArticlDao().insertArchtical(artical)

    fun getSavedArtical()=articaDatabase.getArticlDao().getAllArticle()
    suspend fun deleteArtical(artical :Article)=articaDatabase.getArticlDao().delete(artical)


}