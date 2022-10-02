package com.ysanjeet535.jetpackcomposevideoplayer.ui.video

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.ysanjeet535.jetpackcomposevideoplayer.utils.UiUtils

@Composable
fun FullSizeVideoScreenContent(videoUrl: String) {
    VideoPlayer(videoUrl)
}

@Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red.copy(0.2f))
            .clickable {
                UiUtils.showToast(
                    context = context,
                    message = "$videoUrl is playing now"
                )
            }
    ) {

    }
}