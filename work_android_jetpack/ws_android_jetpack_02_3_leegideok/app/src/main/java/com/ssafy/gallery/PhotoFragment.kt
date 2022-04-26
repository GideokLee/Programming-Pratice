package com.ssafy.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.gallery.databinding.FragmentPhotoBinding
import com.ssafy.gallery.dto.Photo
import com.ssafy.gallery.util.Utils


class PhotoFragment : Fragment() {

    private lateinit var currPhoto: Photo
    private lateinit var binding: FragmentPhotoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoBinding.inflate(inflater,container,false)

        currPhoto = arguments?.getSerializable("photo") as Photo
        val packageName = "com.ssafy.gallery"
        val resId = binding.ivPhoto.resources.getIdentifier(currPhoto.src,"drawable", packageName)
        binding.ivPhoto.setImageResource(resId)

        binding.tvDate.text = Utils.formatter().format(currPhoto.date)
        binding.tvLocation.text = currPhoto.location

        return binding.root
    }

}