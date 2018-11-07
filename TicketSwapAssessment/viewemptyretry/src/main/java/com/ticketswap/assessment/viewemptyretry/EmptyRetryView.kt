package com.ticketswap.assessment.viewemptyretry

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

class EmptyRetryView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private var retryText: String? = null

    private var emptyText: String? = null

    private var retryButtonText: String? = null

    private var buttonBackgroundRes: Int = -1
    private var buttonTextColor: Int = -1
    private var retryTextColor: Int = -1

    private var description: TextView? = null

    private var button: Button? = null

    private fun init(attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.EmptyRetryView)
        retryText = a.getString(R.styleable.EmptyRetryView_erv_RetryText)
        emptyText = a.getString(R.styleable.EmptyRetryView_erv_EmptyText)
        retryButtonText = a.getString(R.styleable.EmptyRetryView_erv_RetryButtonText)
        buttonBackgroundRes = a.getResourceId(R.styleable.EmptyRetryView_erv_ButtonBackground, -1)
        buttonTextColor = a.getResourceId(R.styleable.EmptyRetryView_erv_ButtonTextColor, -1)
        retryTextColor = a.getResourceId(R.styleable.EmptyRetryView_erv_TextColor, -1)
        a.recycle()

        addView(
                (LayoutInflater.from(context).inflate(R.layout.empty_retry_view, this, false) as LinearLayout
                        ).apply {
                    description = findViewById<TextView>(R.id.text_view_emptyRetry_description).apply {
                        if (retryTextColor != -1) setTextColor(ContextCompat.getColor(context, retryTextColor))
                        text = retryText
                    }

                    button = findViewById<Button>(R.id.button_emptyRetry_retry).apply {
                        if (buttonTextColor != -1) setTextColor(ContextCompat.getColor(context, buttonTextColor))
                        text = retryButtonText
                        if (buttonBackgroundRes != -1) setBackgroundResource(buttonBackgroundRes)
                        setOnClickListener { retryClickListener }
                    }
                    (this.layoutParams as LayoutParams).gravity = Gravity.CENTER
                }
        )

    }

    fun showEmpty() {
        visibility = View.VISIBLE
        button?.visibility = View.GONE
        description?.visibility = View.VISIBLE
    }

    fun hideEmpty() {
        visibility = View.GONE
        button?.visibility = View.GONE
        description?.visibility = View.VISIBLE
    }

    private var retryClickListener: (() -> Unit)? = null

    fun showRetry(onClick: () -> Unit) {
        visibility = View.VISIBLE
        retryClickListener = onClick
        button?.visibility = View.VISIBLE
        description?.visibility = View.VISIBLE
        button?.setOnClickListener { retryClickListener }
    }

    fun hideRetry() {
        visibility = View.GONE
        button?.visibility = View.VISIBLE
        description?.visibility = View.VISIBLE
    }
}