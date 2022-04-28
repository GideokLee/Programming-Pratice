package com.ssafy.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), GalleryFragment.Callbacks {
    private val galleryRepository = GalleryRepository.get()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 초기 실행화면 설정
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = GalleryFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
        CoroutineScope(Dispatchers.Main).launch {
            GalleryWorker.count = galleryRepository.getCount()
        }
    }

    // 리사이클러뷰 클릭시 바꿔주기
    override fun onPhotoSelected(photoId: Long) {
        // Fragment에 인자 전달하기
        val fragment = PhotoFragment.newInstance(photoId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        CoroutineScope(Dispatchers.Main).launch {
            val count = galleryRepository.getCount()

            val inputData = Data.Builder()
                .putInt(GalleryWorker.EXTRA_COUNT, count)
                .build()

            val request : OneTimeWorkRequest =
                OneTimeWorkRequestBuilder<GalleryWorker>()
                    .setInputData(inputData)
                    .build()

            val workManager = WorkManager.getInstance(this@MainActivity)
            workManager
                .beginWith(request)
                .enqueue()

            val state = workManager.getWorkInfoByIdLiveData(request.id)
            state.observe(this@MainActivity){ info ->
                val result = info.outputData.getBoolean(GalleryWorker.EXTRA_RESULT, false)
                when(info.state){
                    WorkInfo.State.SUCCEEDED ->{
                        if(result){
                            Toast.makeText(baseContext,"새로 변동된 사항이 있습니다.", Toast.LENGTH_SHORT).show()
                            val fragment = GalleryFragment.newInstance()
                            supportFragmentManager
                                .beginTransaction()
                                .add(R.id.fragment_container, fragment)
                                .commit()
                        }else{
                            Toast.makeText(baseContext,"새로 변동된 사항이 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }


}