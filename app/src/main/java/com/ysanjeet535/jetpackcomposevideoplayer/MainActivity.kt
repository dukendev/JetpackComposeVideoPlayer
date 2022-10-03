package com.ysanjeet535.jetpackcomposevideoplayer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ysanjeet535.jetpackcomposevideoplayer.ui.main.MainScreenContent
import com.ysanjeet535.jetpackcomposevideoplayer.ui.theme.JetpackComposeVideoPlayerTheme
import com.ysanjeet535.jetpackcomposevideoplayer.ui.video.FullSizeVideoScreenContent
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private const val PASSWORD = "TX6Pqb06B00cM5Qm"

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
                            composable(route = Screen.Video.route + "/{url}", arguments = listOf(
                                navArgument("url") {
                                    type = NavType.StringType
                                }
                            )) {
                                val videoUrl = it.arguments?.getString("url")
                                val url = if (videoUrl?.contains(':') == true) {
                                    URLDecoder.decode(
                                        videoUrl,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                } else videoUrl
                                FullSizeVideoScreenContent(url ?: "")
                            }
                        }
                    }
                }
            }
        }
    }
}


sealed class Screen(val route: String) {
    object Video : Screen("video")
    object Main : Screen("main")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                if (arg.contains(':')) {
                    val encodedUrl = URLEncoder.encode(arg, StandardCharsets.UTF_8.toString())
                    append("/$encodedUrl")
                } else
                    append("/$arg")
            }
        }
    }
}