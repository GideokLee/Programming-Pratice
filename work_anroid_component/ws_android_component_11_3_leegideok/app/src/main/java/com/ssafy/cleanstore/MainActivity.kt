package com.ssafy.cleanstore

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ssafy.cleanstore.databinding.ActivityMainBinding
import com.ssafy.cleanstore.service.BoundService

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        val photoUrl = intent.getStringExtra("userPhoto")
        val userName = intent.getStringExtra("userName")
        if(photoUrl !=null && userName != null){
            binding.tvToolbar.text = userName
            Glide.with(this)
                .load(photoUrl)
                .transform(CenterCrop(), RoundedCorners(30))
                .into(binding.ivToolbar)
        }
    }

    override fun onStart() {
        super.onStart()
        if (!BoundServiceConnention.isBound) {
            Intent(this, BoundService::class.java).apply {
                bindService(this, BoundServiceConnention, Context.BIND_AUTO_CREATE)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(BoundServiceConnention)
    }

    // fragment replace
    private fun replaceFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, f)
            .commit()
    }
}

object BoundServiceConnention : ServiceConnection {

    lateinit var boundService: BoundService

    var isBound = false

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as BoundService.MyBinder
        boundService = binder.getService()
        isBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        isBound = false
    }
}