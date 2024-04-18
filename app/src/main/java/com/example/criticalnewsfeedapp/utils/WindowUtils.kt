package com.example.criticalnewsfeedapp.utils

import android.content.Context
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.layout.WindowMetricsCalculator

class WindowUtils {

    companion object {

        fun computeWindowSizeClasses(context: Context): Pair<WindowWidthSizeClass, WindowHeightSizeClass>{
            val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context)
            val width = metrics.bounds.width()
            val height = metrics.bounds.height()
            val density = context.resources.displayMetrics.density
            val windowSizeClass = WindowSizeClass.compute(width/density, height/density)

            return Pair(windowSizeClass.windowWidthSizeClass, windowSizeClass.windowHeightSizeClass)
        }
    }
}