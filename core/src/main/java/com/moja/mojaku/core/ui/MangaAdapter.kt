package com.moja.mojaku.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.moja.mojaku.core.R
import com.moja.mojaku.core.databinding.ItemMangaGridBinding
import com.moja.mojaku.core.domain.model.Manga

class MangaAdapter : RecyclerView.Adapter<MangaAdapter.ListViewHolder>() {

    private var listData = ArrayList<Manga>()
    var onItemClick: ((Manga) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Manga>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_manga_grid, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMangaGridBinding.bind(itemView)

        fun bind(data: Manga) {
            with(binding){
                imgGridPoster.load(data.images){
                    crossfade(true)
                    placeholder(R.drawable.img_placeholder)
                    error(R.drawable.ic_broken)
                }

                tvGridTitle.text = data.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}