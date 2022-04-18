package com.ssafy.comp_08.app2

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.ssafy.comp_08.app2.databinding.IncludeDrawerBinding


class IncludeDrawerActivity : AppCompatActivity() {

    private lateinit var binding: IncludeDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = IncludeDrawerBinding.inflate(layoutInflater).apply {
            mainInclude.mainBtn.setOnClickListener {
                mainDrawerLayout.openDrawer(GravityCompat.START)
            }
        }
        //action bar에 삼선 보이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_format_list_bulleted_24)

        setContentView(binding.root)

        binding.navigationView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item1 -> {
                    Toast.makeText(this@IncludeDrawerActivity, "item1 clicked..", Toast.LENGTH_SHORT).show();
                }
                R.id.item2 -> {
                    Toast.makeText(this@IncludeDrawerActivity, "item2 clicked..", Toast.LENGTH_SHORT).show();
                }
                R.id.item3 -> {
                    Toast.makeText(this@IncludeDrawerActivity, "item3 clicked..", Toast.LENGTH_SHORT).show();
                }
            }
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            false
        }
    }

    // 햄버거 메뉴 선택시 drawer 펼치기
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            }
            else {
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}



