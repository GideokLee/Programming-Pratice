package com.ssafy.gallery

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.gallery.databinding.FragmentGalleryBinding
import com.ssafy.gallery.dto.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    val packageName = "com.ssafy.gallery"
    val resId = view.resources.getIdentifier(url,"drawable", packageName)
    view.setImageResource(resId)
}

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var photoList:MutableList<Photo>
    private lateinit var photoList2:MutableList<Photo>
    private lateinit var galleryRepository: GalleryRepository

    private val photoViewModel:PhotoViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        galleryRepository = GalleryRepository.get()

        initAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(inflater,container,false)

        return binding.root
    }

    private suspend fun getData(): MutableList<Photo>{
        return galleryRepository.getPhotos()
    }

    private fun initAdapter(){
        CoroutineScope(Dispatchers.Main).launch {
            photoList = getData()
            val adapter = context?.let { GalleryAdapter(it, photoList as ArrayList<Photo>) }
            val gridLayoutManager = GridLayoutManager(context,3)
            binding.recyclerView.layoutManager = gridLayoutManager
            binding.recyclerView.adapter = adapter
            binding.recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }
    }

}
