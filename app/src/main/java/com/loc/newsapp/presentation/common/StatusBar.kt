package com.loc.newsapp.presentation.common

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SetStatusBarColor(color: Color) {
    val context = LocalContext.current
    val view = LocalView.current

    if (context is Activity) {
        val window = context.window
        window.statusBarColor = color.toArgb()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = view.windowInsetsController
            controller?.setSystemBarsAppearance(
                0, // Clear the light status bar appearance
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = 0 // Clear the light status bar flag
        }
    }
}