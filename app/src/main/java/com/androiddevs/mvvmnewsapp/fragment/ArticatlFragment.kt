package com.androiddevs.mvvmnewsapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.androiddevs.mvvmnewsapp.NewsActivity
import com.androiddevs.mvvmnewsapp.NewsViewModel
import com.androiddevs.mvvmnewsapp.R
import kotlinx.android.synthetic.main.fragment_article.*

class ArticatlFragment : Fragment(R.layout.fragment_article) {


    lateinit var newsViewmodel: NewsViewModel
    val args: ArticatlFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewmodel = (activity as NewsActivity).newsViewModel
        var artical = args.artical

        if (artical == null)
            Log.e("bakrooo", "null")
        else {
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(artical.url)
            }
            fab.setOnClickListener{
                newsViewmodel.saveArtical(artical)
            }
        }



    }

}