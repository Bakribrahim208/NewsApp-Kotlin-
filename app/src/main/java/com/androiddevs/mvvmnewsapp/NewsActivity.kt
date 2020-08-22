package com.androiddevs.mvvmnewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.mvvmnewsapp.db.AritcleDatabase
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var respoistay=NewsRespoistay(AritcleDatabase(this))
        val viewModelFectory=NewsProviderFectory(respoistay)

        newsViewModel=ViewModelProvider(this,viewModelFectory).get(NewsViewModel::class.java)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

    }
}
