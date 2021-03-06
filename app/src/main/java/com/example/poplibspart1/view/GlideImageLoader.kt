package com.example.poplibspart1.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.poplibspart1.view.interfaces.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String?, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}