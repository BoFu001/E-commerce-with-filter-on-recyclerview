package com.bofu.a20210421_fu_project.extensions
import android.content.res.Resources

fun Int.dpToPx():Int = (this * Resources.getSystem().displayMetrics.density).toInt()