package com.androiddevs.mvvmnewsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.model.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter :RecyclerView.Adapter<NewsAdapter.ArticlViewHolder>()  {
    inner class  ArticlViewHolder(itemview: View):RecyclerView.ViewHolder(itemview)


    private  val differCallback=object:DiffUtil.ItemCallback<Article>(){
      override fun areItemsTheSame( oldItemPosition:Article,  newItemPosition:Article):Boolean{
          return  oldItemPosition.url==newItemPosition.url
      }

        override fun areContentsTheSame( oldItemPosition:Article,  newItemPosition:Article):Boolean{
            return  oldItemPosition==newItemPosition
        }


    }
    var differ=AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlViewHolder {
      return ArticlViewHolder(
         LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false)
      )
    }

    override fun getItemCount(): Int {

        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticlViewHolder, position: Int) {
      var artical=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(artical.urlToImage).into(ivArticleImage)
            tvSource.text=artical.source.name
            tvTitle.text=artical.title
            tvDescription.text=artical.description
            tvPublishedAt.text=artical.publishedAt
            setOnClickListener {
            onItemClickListener?.let { it(artical) }
            }
        }
    }


    private  var onItemClickListener:((Article)->Unit)?=null


    fun setonItemClicklistner(Listener:(Article)->Unit){
        onItemClickListener=Listener
    }
}