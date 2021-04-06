package com.example.root.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView : RecyclerView,
    itemsWrapper: RecyclerViewItemWrapper?
) {

    var adapter = RecyclerViewAdapter(itemsWrapper?.layoutId ?: 0)
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter

    adapter.setData(itemsWrapper?.itemList ?: emptyList())
    adapter.notifyDataSetChanged()
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    if(url != null) {
        Glide.with(view)
            .load(url).skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(view)
    }
}