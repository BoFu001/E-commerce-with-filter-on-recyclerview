package com.bofu.a20210421_fu_project.extensions
import android.view.View
import android.view.ViewTreeObserver

fun <T : View> T.observerHeight(function: (Int) -> Unit) {
    if (height == 0)
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                return function(height)
            }
        })
    else return function(height)
}