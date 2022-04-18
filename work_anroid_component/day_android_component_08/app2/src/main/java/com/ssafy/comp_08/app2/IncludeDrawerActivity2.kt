package com.ssafy.comp_08.app2

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.ssafy.comp_08.app2.databinding.IncludeDrawer2Binding


private const val TAG = "DrawerActivity2_싸피"
class IncludeDrawerActivity2 : AppCompatActivity() {

    private lateinit var binding: IncludeDrawer2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = IncludeDrawer2Binding.inflate(layoutInflater).apply {
            mainInclude2.mainBtnStart.setOnClickListener {
                mainDrawerLayout.openDrawer(GravityCompat.START)
            }
            mainInclude2.mainBtnEnd.setOnClickListener {
                mainDrawerLayout.openDrawer(GravityCompat.END)
            }
        }
        //action bar에 삼선 보이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_format_list_bulleted_24)

        setContentView(binding.root)

        binding.navigationViewStart.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item1 -> {
                    Toast.makeText(this@IncludeDrawerActivity2, "item1 clicked..", Toast.LENGTH_SHORT).show();
                }
                R.id.item2 -> {
                    Toast.makeText(this@IncludeDrawerActivity2, "item2 clicked..", Toast.LENGTH_SHORT).show();
                }
                R.id.item3 -> {
                    Toast.makeText(this@IncludeDrawerActivity2, "item3 clicked..", Toast.LENGTH_SHORT).show();
                }
            }
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            false
        }

        binding.navigationViewEnd.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item1 -> {
                    Toast.makeText(this@IncludeDrawerActivity2, "right-item1 clicked..", Toast.LENGTH_SHORT).show();
                }
                R.id.item2 -> {
                    Toast.makeText(this@IncludeDrawerActivity2, "right-item2 clicked..", Toast.LENGTH_SHORT).show();
                }
                R.id.item3 -> {
                    Toast.makeText(this@IncludeDrawerActivity2, "right-item3 clicked..", Toast.LENGTH_SHORT).show();
                }
            }
            binding.mainDrawerLayout.closeDrawer(GravityCompat.END)
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 햄버거 메뉴 선택시 drawer 펼치기
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected: menuitem : ${item.itemId},  R.menu.menu: ${R.menu.menu}")

        if (item.itemId == android.R.id.home) {
            if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            }
            else {
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)
            }
        }
        else if (item.itemId == R.id.menu) {
            if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                binding.mainDrawerLayout.closeDrawer(GravityCompat.END)
            }
            else {
                binding.mainDrawerLayout.openDrawer(GravityCompat.END)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



