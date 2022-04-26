package com.ssafy.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ssafy.gallery.databinding.ActivityMainBinding
import com.ssafy.gallery.dto.Photo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, GalleryFragment())
            .commit()
    }
    fun onClickListener(view: View) {
        var fragment = PhotoFragment()
        var bundle = Bundle()
        bundle.putSerializable("photo", (view.tag as Photo))
        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, GalleryFragment())
            .commit()
    }
}