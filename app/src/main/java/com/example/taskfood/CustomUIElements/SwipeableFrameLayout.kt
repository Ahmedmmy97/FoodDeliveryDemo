package com.example.taskfood.CustomUIElements

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout


class SwipeableFrameLayout : FrameLayout {
    private var x1 = 0f
    private var x2 = 0f
    private var mSwipeDetectedListener: onSwipeEventDetected? = null

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var result = false
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> x1 = ev.x
            MotionEvent.ACTION_UP -> {
                x2 = ev.x
                val deltaX = x2 - x1
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (deltaX < 0) {
                        result = true
                        if (mSwipeDetectedListener != null) mSwipeDetectedListener!!.swipeLeftDetected()
                    } else if (deltaX > 0) {
                        result = true
                        if (mSwipeDetectedListener != null) mSwipeDetectedListener!!.swipeRightDetected()
                    }
                }
            }
        }
        return result
    }

    interface onSwipeEventDetected {
        fun swipeLeftDetected()
        fun swipeRightDetected()
    }

    fun registerToSwipeEvents(listener: onSwipeEventDetected?) {
        mSwipeDetectedListener = listener
    }

    companion object {
        const val MIN_DISTANCE = 100
    }
}