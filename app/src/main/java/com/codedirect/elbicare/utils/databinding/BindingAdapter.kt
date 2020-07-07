package com.codedirect.elbicare.utils.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("setImgSrc")
fun setImgSrc(view: ImageView, message: Int) {
    view.setImageResource(message)
}