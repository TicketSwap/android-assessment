package com.ticketswap.assessment.view.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object CopyUtil {
    fun copy(context: Context, label: String, text: String) {
        (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).apply {
            primaryClip = ClipData.newPlainText(label, text)
        }
    }
}