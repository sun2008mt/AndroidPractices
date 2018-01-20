package com.whu.androidpractices

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent: Intent?
        btn_shared_preference.setOnClickListener {
            intent = Intent(this@MainActivity, SharedPreferenceActivity::class.java)
            startActivity(intent)
        }

        btn_file_storage.setOnClickListener {
            intent = Intent(this@MainActivity, StorageActivity::class.java)
            startActivity(intent)
        }

        btn_intent_test.setOnClickListener {
            intent = Intent(this@MainActivity, IntentActivity::class.java)
            startActivity(intent)
        }
    }
}
