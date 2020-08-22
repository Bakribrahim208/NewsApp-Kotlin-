package com.androiddevs.mvvmnewsapp.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.mvvmnewsapp.NewsActivity
import com.androiddevs.mvvmnewsapp.NewsAdapter
import com.androiddevs.mvvmnewsapp.NewsViewModel
import com.androiddevs.mvvmnewsapp.R
import kotlinx.android.synthetic.main.fragment_saved_news.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {


    lateinit var newsViewmodel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewmodel = (activity as NewsActivity).newsViewModel

        setupRecyclerView()
        newsAdapter.setonItemClicklistner {
            val bundle=Bundle().apply {
                putSerializable("artical",it)
            }
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articatlFragment,bundle)
        }



        var ItemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            androidx.recyclerview.widget.ItemTouchHelper.UP or androidx.recyclerview.widget.ItemTouchHelper.DOWN,
            androidx.recyclerview.widget.ItemTouchHelper.LEFT or androidx.recyclerview.widget.ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val postion = viewHolder.adapterPosition
                var artical = newsAdapter.differ.currentList[postion]
                newsViewmodel.deleteArtical(artical)
                Snackbar.make(view, "deleted succesfully", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        newsViewmodel.saveArtical(artical)

                    }
                        .show()
                }
            }
        }

        ItemTouchHelper(ItemTouchHelper).apply {
            attachToRecyclerView(rvSavedNews)
        }
        newsAdapter.setonItemClicklistner {
            val bundle = Bundle().apply {
                putSerializable("artical", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articatlFragment,
                bundle
            )
        }

        newsViewmodel.getFavouriteArticals().observe(viewLifecycleOwner, Observer { articals ->
            newsAdapter.differ.submitList(articals)

        })
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()

        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}