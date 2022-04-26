package com.ssafy.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.gallery.databinding.ListItemGalleryBinding
import com.ssafy.gallery.dto.Photo


class GalleryAdapter(val context: Context, private val list: ArrayList<Photo>) :
        RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ListItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // binding을 객체로 받아 데이터와 연결시켜준다.
        fun bind(item: Photo) {
            binding.apply {
                photo = item
                activity = context as MainActivity
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var listItemBinding = ListItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dto = list[position]
        holder.apply {
            bind(dto)
            itemView.tag = dto
        }
    }

    override fun getItemCount() = list.size
}