package com.ssafy.comp_05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.comp_05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            btnImage.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, ImageActivity::class.java)
                )
            }

            btnMedia.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, MediaActivity::class.java)
                )
            }

            btnContacts.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, ContactsActivity::class.java)
                )
            }
        }
    }
}