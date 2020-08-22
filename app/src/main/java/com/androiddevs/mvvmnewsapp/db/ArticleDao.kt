package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete

import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androiddevs.mvvmnewsapp.model.Article

@Dao
interface ArticleDao {

 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun insertArchtical(article:Article):Long

    @Query("SELECT * FROM articlex")
      fun getAllArticle():LiveData<List<Article>>


    @Delete
    suspend fun delete(article:Article)

}