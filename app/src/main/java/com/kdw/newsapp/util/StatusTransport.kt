package com.kdw.newsapp.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi

@Suppress("DEPRECATION")
object StatusTransport {

    @RequiresApi(Build.VERSION_CODES.M)
    fun makeStatusTransport(activity: Activity) {
        val status = activity.window.decorView

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            status.windowInsetsController?.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            , WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)

            val win = activity.window
            win.statusBarColor = Color.TRANSPARENT
        } else
            status.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val wd = activity.window
        wd.statusBarColor = Color.TRANSPARENT
    }
}