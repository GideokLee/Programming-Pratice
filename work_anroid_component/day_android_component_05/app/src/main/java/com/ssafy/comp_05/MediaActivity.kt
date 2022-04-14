package com.ssafy.comp_05

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.ssafy.comp_05.databinding.ActivityMediaBinding

data class MusicDto(val id: Long, val albumId: Long, val title: String, val artist: String)

private const val TAG = "MediaActivity_싸피"
class MediaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaBinding
    private val musicList = mutableListOf<MusicDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionlistener = object : PermissionListener {
            override fun onPermissionGranted() {
                initData()
                initView()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(this@MediaActivity,
                    "스토리지에 접근 권한을 허가해주세요",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        TedPermission.create()
            .setPermissionListener(permissionlistener)
            .setDeniedMessage("권한을 허용해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
            .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .check()
    }

    private fun initData() {
        getAudio()?.use {
            if (it.moveToFirst()) {
                do {
                    val trackId = it.getLong(it.getColumnIndex(MediaStore.Audio.Media._ID))
                    val albumId = it.getLong(it.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                    val title = it.getString(it.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val artist = it.getString(it.getColumnIndex(MediaStore.Audio.Media.ARTIST))

                    Log.d(TAG, "trackId:$trackId, albumId:$albumId, album_titme:$title, artist:$artist")

                    musicList.add(MusicDto(trackId, albumId, title, artist))

                } while(it.moveToNext())
            }
        }
    }

    private fun getAudio(): Cursor? {
        val resolver = contentResolver
        val queryUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        // 정렬
        val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"

        // 가져올 컬럼명
        val what = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
        )

        return resolver.query(queryUri, what, null, null, sortOrder)
    }

    private fun initView() {
        val musicAdapter = MusicAdapter(this, musicList)

        binding.recyclerView.apply {
            adapter = musicAdapter
            layoutManager = LinearLayoutManager(this@MediaActivity)
        }
    }
}

class MusicAdapter(private val context: Context, private val musicList: MutableList<MusicDto>)
    : RecyclerView.Adapter<MusicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.media_item, parent, false)

        return MusicViewHolder(itemView).apply {
            itemView.setOnClickListener {

                val uri: Uri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    musicList[adapterPosition].id
                )

                Log.d(TAG, "onCreateViewHolder: $uri")

                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(musicList[position])
    }

    override fun getItemCount(): Int = musicList.size
}

class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var title = itemView.findViewById(R.id.tv_title) as TextView
    private var artist = itemView.findViewById(R.id.tv_artist) as TextView

    fun bind(music: MusicDto) {
        title.text = music.title
        artist.text = music.artist
    }
}