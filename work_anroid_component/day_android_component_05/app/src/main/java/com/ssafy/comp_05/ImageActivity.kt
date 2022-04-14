package com.ssafy.comp_05

import android.Manifest
import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.DateFormat
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import com.ssafy.comp_05.databinding.ActivityImageBinding
import java.util.*


private const val TAG = "ImageActivity_싸피"
class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionlistener = object : PermissionListener {
            override fun onPermissionGranted() {
                initView()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(this@ImageActivity,
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

    private fun initView() {
        getImage()?.use { cursor ->
            if (cursor.moveToFirst()) {
                // 컬럼의 열 인덱스 확인.
                val idColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
                val titleColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE)
                val dateTakenColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN)
                val isoColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ISO)
                val fNumberColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.F_NUMBER)

                do {
                    // 열 인덱스로 데이터 구하기.
                    val id = cursor.getLong(idColNum)
                    val title = cursor.getString(titleColNum)
                    val dateTaken = cursor.getLong(dateTakenColNum)
                    val iso = cursor.getString(isoColNum)
                    val fNumber = cursor.getString(fNumberColNum)

                    val dateTakenString = DateFormat.format("yyyy/MM/dd (E) kk:mm:ss", Date(dateTaken)).toString()

                    val imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    Log.d(TAG, "imageUri: ${imageUri}") //  content://media/external/images/media/31

                    binding.tvTitle.text = title
                    binding.tvDateTaken.text = "촬영일시: $dateTakenString"
                    binding.tvIso.text = "ISO: ${iso}\nF-Number: ${fNumber}"
                    binding.ivOne.setImageURI(imageUri)

                } while (cursor.moveToNext())
            }
        }
    }

    private fun getImage(): Cursor? {
        val resolver = contentResolver
        var queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        // 가져올 컬럼명(Projection)
        val what = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATE_TAKEN,
            MediaStore.Images.ImageColumns.F_NUMBER,
            MediaStore.Images.ImageColumns.ISO
        )

        // Android version 대응. API Level 29 이상.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // 정렬 & 건수제한
            val bundle = Bundle().apply {
                putStringArray(ContentResolver.QUERY_ARG_SORT_COLUMNS, arrayOf(MediaStore.Images.ImageColumns.DATE_TAKEN))
                putInt(ContentResolver.QUERY_ARG_SORT_DIRECTION, ContentResolver.QUERY_SORT_DIRECTION_DESCENDING)
                putInt(ContentResolver.QUERY_ARG_OFFSET, 0)
                putInt(ContentResolver.QUERY_ARG_LIMIT, 2)
            }

            return resolver.query(queryUri, what, bundle, null)

        }
        else {
            // 이전버전 정렬 & 건수제한
            queryUri = queryUri.buildUpon().appendQueryParameter("limit", "2").build()
            Log.d(TAG, "getImage: $queryUri") //content://media/external/images/media?limit=3
            val orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"

            return resolver.query(queryUri, what, null, null, orderBy)
        }
    }
}