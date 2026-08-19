package com.aroma.healthguardai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aroma.healthguardai.ui.chat.ChatScreen
import com.aroma.healthguardai.ui.home.HomeScreen
import com.aroma.healthguardai.ui.ondevice.OnDeviceTestScreen
@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    )
    {

        composable("home") {

            HomeScreen(
                onAskAI = {
                    navController.navigate("chat")
                }
            )
        }

        composable("chat") {

            ChatScreen()
        }
        composable("ondevice") {
            OnDeviceTestScreen()
        }
    }
}