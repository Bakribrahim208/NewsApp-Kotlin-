package com.androiddevs.mvvmnewsapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.mvvmnewsapp.NewsActivity
import com.androiddevs.mvvmnewsapp.NewsAdapter
import com.androiddevs.mvvmnewsapp.NewsViewModel
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news){

    lateinit var newsViewmodel:NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewmodel=(activity as NewsActivity).newsViewModel

        setupRecyclerView()
        newsAdapter.setonItemClicklistner {
            val bundle=Bundle().apply {
                putSerializable("artical",it)
            }
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articatlFragment,bundle)
        }
        newsViewmodel.breakingNes.observe(viewLifecycleOwner, Observer {response->

            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {
                        newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()

                    response.message?.let {
                            message ->
                        Log.e("bakrooooo",message)
                    }
                }

                is Resource.Loading->{
                    ShowProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility=INVISIBLE
    }

    private fun ShowProgressBar(){
        paginationProgressBar.visibility=VISIBLE
    }
    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()

        rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager=LinearLayoutManager(activity)
        }
    }








}