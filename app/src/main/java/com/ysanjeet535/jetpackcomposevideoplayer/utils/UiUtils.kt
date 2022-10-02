package com.ysanjeet535.jetpackcomposevideoplayer.utils

import android.content.Context
import android.widget.Toast

class UiUtils {
    companion object {
        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}