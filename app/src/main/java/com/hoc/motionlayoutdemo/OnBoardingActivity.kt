package com.hoc.motionlayoutdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.minusAssign
import androidx.core.view.plusAssign
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kotlinx.android.synthetic.main.view_pager_item.view.*
import kotlin.math.absoluteValue

data class ViewPagerItem(
  val title: String,
  val description: String,
  @DrawableRes val image: Int
)

class OnBoardingActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_on_boarding)

    val viewPagerItems = listOf(
      ViewPagerItem(
        title = "Android - The best platform ever!",
        description = "The world’s most popular mobile OS.From phones and watches to cars and TVs, customize your digital life with Android.",
        image = R.drawable.android
      ),
      ViewPagerItem(
        title = "Swift",
        description = "Swift is a fantastic way to write software, whether it’s for phones, desktops, servers, or anything" +
            " else that runs code. It’s a safe, fast, and interactive programming language that combines the best in modern" +
            " language thinking with wisdom from the wider Apple engineering culture and the diverse contributions from its " +
            "open-source community. ",
        image = R.drawable.swift
      ),
      ViewPagerItem(
        title = "Android - The best platform ever!",
        description = "The world’s most popular mobile OS.From phones and watches to cars and TVs, customize your digital life with Android.",
        image = R.drawable.android
      ),
      ViewPagerItem(
        title = "Swift",
        description = "Swift is a fantastic way to write software, whether it’s for phones, desktops, servers, or anything" +
            " else that runs code. It’s a safe, fast, and interactive programming language that combines the best in modern" +
            " language thinking with wisdom from the wider Apple engineering culture and the diverse contributions from its " +
            "open-source community. ",
        image = R.drawable.swift
      ),
      ViewPagerItem(
        title = "Android - The best platform ever!",
        description = "The world’s most popular mobile OS.From phones and watches to cars and TVs, customize your digital life with Android.",
        image = R.drawable.android
      )
    )
    view_pager.adapter = object : PagerAdapter() {
      override fun isViewFromObject(view: View, `object`: Any) = view === `object`
      override fun getCount() = viewPagerItems.size
      override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container -= `object` as View
      }

      override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return LayoutInflater.from(container.context)
          .inflate(R.layout.view_pager_item, container, false)
          .apply {
            val item = viewPagerItems[position]
            image_view.setImageResource(item.image)
            text_title.text = item.title
            text_description.text = item.description
          }
          .also { container += it }
      }
    }
    view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(state: Int) {
      }

      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        root_main_layout.progress = (position + positionOffset) / (viewPagerItems.size - 1)
      }

      override fun onPageSelected(position: Int) {
      }
    })

    previousButton.setOnClickListener {
      --view_pager.currentItem
    }
    nextButton.setOnClickListener {
      ++view_pager.currentItem
    }
    finishButton.setOnClickListener {
      Toast.makeText(this@OnBoardingActivity, "Finish", Toast.LENGTH_LONG).show()
    }

    view_pager.setPageTransformer(true) { page, position ->
      page.run {
        when {
          position <= 0f -> {
            translationX = 0f
            scaleX = 1f
            scaleY = 1f
          }
          position <= 1f -> {
            val scaleFactor = 0.75f + (1 - 0.75f) * (1 - position.absoluteValue)
            alpha = 1 - position
            pivotY = 0.5f * height
            translationX = width * -position
            scaleX = scaleFactor
            scaleY = scaleFactor
          }
        }
      }
    }
  }
}
