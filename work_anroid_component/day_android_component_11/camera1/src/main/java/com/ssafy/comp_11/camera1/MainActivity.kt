package com.ssafy.comp_11.camera1

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                startActivity(Intent(this@MainActivity, CameraActivity::class.java))
                finish()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(this@MainActivity,
                    "카메라 권한이 거부되었습니다.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setDeniedMessage("[설정] 에서 카메라 접근 권한을 부여해야만 사용이 가능합니다.")
            .setPermissions(Manifest.permission.CAMERA)
            .check()
    }
}