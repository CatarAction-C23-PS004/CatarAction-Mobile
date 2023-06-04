package com.cataractaction

import android.app.Activity
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cataractaction.ui.common.Keyboard
import com.cataractaction.ui.common.keyboardAsState
import com.cataractaction.ui.components.main.BottomBar
import com.cataractaction.ui.navigation.Screen
import com.cataractaction.ui.screen.GoogleViewModel
import com.cataractaction.ui.screen.article.ArticleScreen
import com.cataractaction.ui.screen.camera.CameraScreen
import com.cataractaction.ui.screen.cataractcheck.CataractCheckScreen
import com.cataractaction.ui.screen.coming.ComingScreen
import com.cataractaction.ui.screen.history.HistoryScreen
import com.cataractaction.ui.screen.home.HomeScreen
import com.cataractaction.ui.screen.login.LoginScreen
import com.cataractaction.ui.screen.pager.PagerScreen
import com.cataractaction.ui.screen.profile.ProfileScreen
import com.cataractaction.ui.screen.profile.ProfileViewModel
import com.cataractaction.ui.screen.register.RegisterScreen
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CataractActionApp(
    modifier: Modifier = Modifier, navHostController: NavHostController = rememberNavController(),
    viewModelProfile: ProfileViewModel = hiltViewModel(),
    viewModelGoogle: GoogleViewModel = hiltViewModel()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isKeyboardOpen by keyboardAsState()

    val state by viewModelGoogle.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        if (viewModelProfile.getSignedInUser() != null) {
            navHostController.popBackStack()
            navHostController.navigate(Screen.Home.route) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error -> Toast.makeText(context, error, Toast.LENGTH_LONG).show() }
    }

    LaunchedEffect(key1 = state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            Toast.makeText(context, "Sign in succesful", Toast.LENGTH_LONG).show()

            navHostController.popBackStack()
            navHostController.navigate(Screen.Home.route) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            viewModelGoogle.resetState()
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModelGoogle.viewModelScope.launch {
                    val signInResult =
                        viewModelGoogle.signInWithIntent(intent = result.data ?: return@launch)
                    viewModelGoogle.onSignInResult(signInResult)
                }
            }
        })

    val onSignInClick = {
        viewModelGoogle.viewModelScope.launch {
            val signInIntentSender = viewModelGoogle.getGoogleAuth(context)
            launcher.launch(
                IntentSenderRequest.Builder(
                    signInIntentSender ?: return@launch
                ).build()
            )
        }
    }

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
            startDestination = Screen.Pager.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Pager.route) {
                PagerScreen(navHostController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navHostController) { onSignInClick() }
            }
            composable(Screen.Register.route) {
                RegisterScreen(navHostController) { onSignInClick() }
            }
            composable(Screen.Home.route) {
                HomeScreen(userData = viewModelProfile.getSignedInUser(), navHostController)
            }
            composable(Screen.Article.route) {
                ArticleScreen(navHostController)
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(userData = viewModelProfile.getSignedInUser(), navHostController)
            }
            composable(
                Screen.CataractCheck.route,
                listOf(navArgument("uriFile") { type = NavType.StringType })
            ) {
                CataractCheckScreen(
                    navHostController,
                    uriFile = Uri.parse(Uri.decode(it.arguments?.getString("uriFile")))
                )
            }
            composable(Screen.Coming.route) {
                ComingScreen(navHostController)
            }
            composable(Screen.Camera.route) {
                CameraScreen(navHostController)
            }
        }
    }
}


