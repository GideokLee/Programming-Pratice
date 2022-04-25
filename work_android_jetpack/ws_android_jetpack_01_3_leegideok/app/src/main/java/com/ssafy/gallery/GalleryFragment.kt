package com.ssafy.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.ssafy.gallery.dao.GalleryDao
import com.ssafy.gallery.databinding.FragmentGalleryBinding
import com.ssafy.gallery.dto.Photo

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    val packageName = "com.ssafy.gallery"
    val resId = view.resources.getIdentifier(url,"drawable", packageName)
    view.setImageResource(resId)
}

class GalleryFragment : Fragment() {
    private lateinit var dao: GalleryDao
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var photoList:MutableList<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = GalleryDao()
        activity?.let { dao.dbOpenHelper(it.applicationContext) }
        dao.open()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(inflater,container,false)
        photoList = dao.selectAll()

        val adapter = context?.let { GalleryAdapter(it, photoList as ArrayList<Photo>) }
        val gridLayoutManager = GridLayoutManager(context,3)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        return binding.root
    }


    override fun onDestroy() {
        dao.close()
        super.onDestroy()
    }

}
