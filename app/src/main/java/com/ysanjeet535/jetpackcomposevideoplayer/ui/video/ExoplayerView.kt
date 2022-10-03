package com.ysanjeet535.jetpackcomposevideoplayer.ui.video

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.ysanjeet535.jetpackcomposevideoplayer.utils.UiUtils


@Composable
fun VideoPlayerView(sourceUrl: String) {

    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }

    UiUtils.showToast(context, sourceUrl)

    LaunchedEffect(sourceUrl) {


        val httpDataSourceFactory: HttpDataSource.Factory =
            DefaultHttpDataSource.Factory()

        val mediaItem = MediaItem.fromUri(sourceUrl)
        val source = DashMediaSource.Factory(httpDataSourceFactory)
            .createMediaSource(mediaItem)

        exoPlayer.setMediaSource(source)
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()

    }

    AndroidView(
        factory = {
            StyledPlayerView(context).apply {
                player = exoPlayer
            }
        }
    )

}

@Composable
fun ExoPlayerUI(videoURL: String) = Column(
    Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {

    val mContext = LocalContext.current

    UiUtils.showToast(mContext, videoURL)
    // Initializing ExoPLayer
    val mExoPlayer = remember(mContext) {
        ExoPlayer.Builder(mContext).build().apply {

            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(videoURL))
                .build()
            setMediaItem(mediaItem)
            playWhenReady = true
            prepare()

        }
    }


    // Implementing ExoPlayer
    AndroidView(factory = { context ->
        StyledPlayerView(context).apply {
            player = mExoPlayer
        }
    })
}