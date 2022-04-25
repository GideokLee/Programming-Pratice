package com.ssafy.bookstore

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.ssafy.bookstore.databinding.ActivityMainBinding
import com.ssafy.bookstore.service.BoundService
import com.ssafy.cleanstore.fragment.MainFragment
import com.ssafy.cleanstore.fragment.StoreFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(MainFragment())

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            tabLayoutMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val selectedFragment = when (tab!!.position) {
                        0 -> MainFragment()
                        1 -> StoreFragment()
                        else -> null
                    }
                    replaceFragment(selectedFragment!!)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

    }
    override fun onStart() {
        super.onStart()

        if (!BoundServiceConnection.isBound) {
            Intent(this, BoundService::class.java).also {
                bindService(it, BoundServiceConnection, Context.BIND_AUTO_CREATE)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (BoundServiceConnection.isBound) {
            unbindService(BoundServiceConnection)
        }
    }
    private fun replaceFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout_main, f)
            .commit()
    }
}


// Service Bind
object BoundServiceConnection : ServiceConnection {

    lateinit var myService: BoundService
    var isBound = false

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as BoundService.MyBinder
        myService = binder.getService()
        isBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        isBound = false
    }
}