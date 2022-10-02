package com.ysanjeet535.jetpackcomposevideoplayer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ysanjeet535.jetpackcomposevideoplayer.ui.main.MainScreenContent
import com.ysanjeet535.jetpackcomposevideoplayer.ui.theme.JetpackComposeVideoPlayerTheme
import com.ysanjeet535.jetpackcomposevideoplayer.ui.video.FullSizeVideoScreenContent

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeVideoPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(scaffoldState = scaffoldState) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Main.route
                        ) {
                            composable(route = Screen.Main.route) {
                                MainScreenContent(navController)
                            }
                            composable(route = Screen.Video.route) {
                                val videoUrl = it.arguments?.getString("id")
                                requireNotNull(videoUrl) {
                                    Log.e(Screen.Main.route, "video id null")
                                }
                                FullSizeVideoScreenContent(videoUrl)
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Main : Screen("Main")
    object Video : Screen("Video/{id}") {
        fun createRoute(url: String) = "Video/$url"
    }
}