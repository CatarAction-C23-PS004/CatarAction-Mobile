package com.cataractaction

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cataractaction.ui.common.Keyboard
import com.cataractaction.ui.common.keyboardAsState
import com.cataractaction.ui.components.main.BottomBar
import com.cataractaction.ui.navigation.Screen
import com.cataractaction.ui.screen.article.ArticleScreen
import com.cataractaction.ui.screen.cataractcheck.CataractCheckScreen
import com.cataractaction.ui.screen.history.HistoryScreen
import com.cataractaction.ui.screen.home.HomeScreen
import com.cataractaction.ui.screen.login.LoginScreen
import com.cataractaction.ui.screen.pager.PagerScreen
import com.cataractaction.ui.screen.profile.ProfileScreen
import com.cataractaction.ui.screen.register.RegisterScreen

@Composable
fun CataractActionApp(
    modifier: Modifier = Modifier, navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isKeyboardOpen by keyboardAsState() // t

    Scaffold(
        bottomBar = {
            when (currentRoute) {
                Screen.Home.route -> BottomBar(navHostController, currentRoute)
                Screen.Article.route -> {
                    if (isKeyboardOpen == Keyboard.Closed) {
                        BottomBar(navHostController, currentRoute)
                    }
                }

                Screen.History.route -> BottomBar(navHostController, currentRoute)
                Screen.Profile.route -> BottomBar(navHostController, currentRoute)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Pager.route) {
                PagerScreen(navigateToRegister = {
                    navHostController.navigate(Screen.Register.route)
                })
            }
            composable(Screen.Login.route) {
                LoginScreen(navigate = {
                    navHostController.navigate(Screen.Register.route)
                })
            }
            composable(Screen.Register.route) {
                RegisterScreen(navigate = {
                    navHostController.navigate(Screen.Login.route)
                })
            }
            composable(Screen.Home.route) {
                HomeScreen(navigatetoCheck = {
                    navHostController.navigate(Screen.CataractCheckScreen.route)
                })
            }
            composable(Screen.Article.route) {
                ArticleScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.CataractCheckScreen.route) {
                CataractCheckScreen()
            }
        }
    }
}


