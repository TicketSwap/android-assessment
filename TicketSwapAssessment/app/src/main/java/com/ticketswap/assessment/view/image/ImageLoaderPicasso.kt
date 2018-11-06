package com.ticketswap.assessment.view.image

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoaderPicasso(val context: Context) : ImageLoader {
    override fun load(url: String?, placeHolder: Int, imageView: ImageView) {
        Picasso.with(context).load(url).transform(CircleTransform()).placeholder(placeHolder).into(imageView)
    }
}