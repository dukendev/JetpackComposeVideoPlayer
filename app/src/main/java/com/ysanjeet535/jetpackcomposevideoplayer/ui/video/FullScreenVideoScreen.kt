package com.ysanjeet535.jetpackcomposevideoplayer.ui.video

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ysanjeet535.jetpackcomposevideoplayer.utils.StringUtils.Companion.checkAndGetYoutubeURL
import com.ysanjeet535.jetpackcomposevideoplayer.utils.UiUtils

const val DEFAULT_URL = "https://www.youtube.com/watch?v=dPWYUELwIdM"

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
            .padding(32.dp)
            .clickable {
                UiUtils.showToast(
                    context = context,
                    message = "$videoUrl is playing now"
                )
            }
    ) {
        ExoPlayerUI(videoUrl.checkAndGetYoutubeURL())
    }
}

