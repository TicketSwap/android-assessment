package com.ticketswap.assessment.view.image

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String?, placeHolder: Int, imageView: ImageView)
}