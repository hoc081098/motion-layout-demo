package com.hoc.motionlayoutdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_collapsing_toolbar.*

class CollapsingToolbarActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_collapsing_toolbar)

    recycler.run {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(this@CollapsingToolbarActivity)
      adapter = object : RecyclerView.Adapter<VH>() {
        val list = List(100) { "Item $it" }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
          .inflate(android.R.layout.simple_list_item_1, parent, false)
          .let(::VH)

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(list[position])
      }
    }
  }

  private class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView = itemView.findViewById<TextView>(android.R.id.text1)
    fun bind(s: String) {
      Log.d("###", "bind '$s'")
      textView.text = s
    }
  }
}
