package com.eugenics.tapthecar.domain.util

import android.content.res.Resources
import android.util.DisplayMetrics


fun Int.dpPx() =
    this * Resources.getSystem().displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

fun convertPixelsToDp(px: Float): Float {
    val metrics = Resources.getSystem().displayMetrics
    return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}