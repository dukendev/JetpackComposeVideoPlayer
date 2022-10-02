package com.ysanjeet535.jetpackcomposevideoplayer.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ysanjeet535.jetpackcomposevideoplayer.R
import com.ysanjeet535.jetpackcomposevideoplayer.Screen

@Composable
fun MainScreenContent(navController: NavController) {

    Column {
        VideoUrlInputContainer(navController)
    }
}

@Composable
fun VideoUrlInputContainer(navController: NavController) {
    var videoUrl by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan.copy(alpha = 0.5f))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = videoUrl, onValueChange = { videoUrl = it })
        Button(onClick = {
            if (videoUrl.isNotEmpty()) {
                navController.navigate(Screen.Video.createRoute(videoUrl))
            }
        }) {
            Text(text = stringResource(R.string.submit_video_url))
        }
    }
}