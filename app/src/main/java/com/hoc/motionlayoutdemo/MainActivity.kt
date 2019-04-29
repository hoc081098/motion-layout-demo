package com.hoc.motionlayoutdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    on_boarding_button.setOnClickListener {
      startActivity(Intent(this@MainActivity, OnBoardingActivity::class.java))
    }

    collapsing_toolbar_button.setOnClickListener {
      startActivity(Intent(this@MainActivity, CollapsingToolbarActivity::class.java))
    }
  }
}
