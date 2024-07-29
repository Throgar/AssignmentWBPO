package com.example.assignmentwbpo.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.assignmentwbpo.R



fun AppCompatImageView.loadImage(image: String?) {
    Glide.with(this.context)
        .load(image?.toUri()?.buildUpon()?.scheme("https")?.build())
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .into(this)
}
