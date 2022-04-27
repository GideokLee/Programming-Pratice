package com.ssafy.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.gallery.databinding.ListItemGalleryBinding

// 데이터 바인딩을 사용한 RecyclerView Adapter
class GalleryAdapter(private val galleries: List<Photo>) : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {

    // list_item_gallery와 연결되는 GalleryHolder
    inner class GalleryHolder(private val binding: ListItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = PhotoViewModel()
        }
        fun bind(dto: Photo) {
            // 각 아이템의 Photo 객체를 PhotoViewModel에 연결시킨다.
            binding.apply {
                viewModel?.photo = dto

                // Binding 실행
                // 변수 또는 관찰 가능한 객체가 변경될 때 결합은 다음 프레임 이전에 변경되도록 예약됩니다.
                // 하지만 결합이 즉시 실행되어야 하는 때도 있습니다.
                // 이럴 때 강제로 실행하려면 executePendingBindings() 메서드를 사용하세요.
                executePendingBindings()

                // 클릭 리스너 연결
                itemView.setOnClickListener {
                    itemClickListner.onClick(it, dto, adapterPosition)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        // list_item_gallery와 연결
        val binding = DataBindingUtil.inflate<ListItemGalleryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_gallery,
            parent,
            false
        )
        return GalleryHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val gallery = galleries[position]
        holder.bind(gallery)
    }

    override fun getItemCount() = galleries.size

    interface ItemClickListener {
        fun onClick(view: View, dto:Photo, position: Int)
    }

    private lateinit var itemClickListner: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}

