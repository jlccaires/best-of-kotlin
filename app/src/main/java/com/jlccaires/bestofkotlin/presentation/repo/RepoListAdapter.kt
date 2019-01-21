package com.jlccaires.bestofkotlin.presentation.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jlccaires.bestofkotlin.R
import com.jlccaires.bestofkotlin.data.dto.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_repo_item.view.*

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    private val mData = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemVIew = LayoutInflater.from(parent.context).inflate(R.layout.layout_repo_item, parent, false)
        return RepoViewHolder(itemVIew)
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.itemView.tvRepoName.text = mData[position].name
        holder.itemView.tvAuthor.text = mData[position].owner.login
        holder.itemView.tvStars.text = mData[position].stargazersCount.toString()
        holder.itemView.tvForks.text = mData[position].forksCount.toString()
        Picasso.get()
                .load(mData[position].owner.avatarURL)
                .into(holder.itemView.ivAuthor)

    }

    fun addAll(repos: List<Item>) {
        val lastCount = itemCount
        mData.addAll(repos)
        notifyItemRangeInserted(lastCount, repos.size)
    }

    fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}