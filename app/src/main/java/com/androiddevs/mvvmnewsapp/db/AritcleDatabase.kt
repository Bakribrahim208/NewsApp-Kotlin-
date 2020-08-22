package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.model.Article

@Database(entities = [Article::class],
           version =  1     )
@TypeConverters(Converter::class)
abstract  class AritcleDatabase () :RoomDatabase(){

 abstract   fun  getArticlDao(): ArticleDao

    companion object{
        private  var instance: AritcleDatabase?=null
        private  val lock=Any()//to make sure only single instance of database
      operator  fun invoke(context: Context)=
          instance ?: synchronized(
              lock
          ){
          instance
              ?: createDatabase(
                  context
              ).also{
              instance =it
          }
      }

        private  fun createDatabase(context:Context)=
            Room.databaseBuilder(context.applicationContext,
                AritcleDatabase::class.java,
                 "artical_db.db"
                ).build()
    }


}