package com.ssafy.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ssafy.gallery.database.GalleryDao
import com.ssafy.gallery.util.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_PHOTO_ID = "photo_id"
class PhotoFragment : Fragment() {

    private var photo: Photo = Photo()
    private var photoId: Long = 0L
    private lateinit var textPhotoLocation : TextView
    private lateinit var textPhotoDate : TextView
    private lateinit var imagePhoto: ImageView
    private val galleryRepository = GalleryRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 넘어온 ID값 받기
        photoId = arguments?.getSerializable(ARG_PHOTO_ID) as Long
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo, container, false)
        textPhotoLocation = view.findViewById(R.id.textPhotoLocation)
        textPhotoDate = view.findViewById(R.id.textPhotoDate)
        imagePhoto = view.findViewById(R.id.imagePhoto)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            photo = galleryRepository.getPhoto(photoId)
            updateUI(photo)
        }
    }

    private fun updateUI(photo: Photo) {
        textPhotoLocation.apply {
            text = photo.location
        }

        textPhotoDate.apply {
            val sdf = Utils.formatter().format(photo.date).toString()
            text = sdf
        }

        imagePhoto.apply {
            val redId = view?.resources?.getIdentifier(photo.src, "drawable", context.packageName)
            if (redId != null) {
                setImageResource(redId)
            }
        }
    }

    companion object {
        // Argument에 Bundle을 넘겨 ID 값을 저장하는 Instance 함수 구현
        fun newInstance(photoId: Long): PhotoFragment {
            val args = Bundle().apply {
                putSerializable(ARG_PHOTO_ID, photoId)
            }

            return PhotoFragment().apply {
                arguments = args
            }
        }
    }
}