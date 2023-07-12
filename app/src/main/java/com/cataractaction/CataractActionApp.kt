package com.cataractaction

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cataractaction.core.domain.model.RealtimeDBUser
import com.cataractaction.ui.common.Keyboard
import com.cataractaction.ui.common.keyboardAsState
import com.cataractaction.ui.components.main.BottomBar
import com.cataractaction.ui.navigation.Screen
import com.cataractaction.ui.screen.article.ArticleScreen
import com.cataractaction.ui.screen.camera.CameraScreen
import com.cataractaction.ui.screen.cataractcheck.CataractCheckScreen
import com.cataractaction.ui.screen.cataractresult.CataractResultScreen
import com.cataractaction.ui.screen.coming.ComingScreen
import com.cataractaction.ui.screen.detailarticle.DetailArticleScreen
import com.cataractaction.ui.screen.history.HistoryScreen
import com.cataractaction.ui.screen.home.HomeScreen
import com.cataractaction.ui.screen.login.LoginScreen
import com.cataractaction.ui.screen.pager.PagerScreen
import com.cataractaction.ui.screen.profile.ProfileScreen
import com.cataractaction.ui.screen.profile.ProfileViewModel
import com.cataractaction.ui.screen.register.RegisterScreen
import com.cataractaction.ui.screen.viewmodel.GoogleViewModel
import com.cataractaction.ui.screen.viewmodel.RealtimeDbViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@Composable
fun CataractActionApp(
    modifier: Modifier = Modifier, navHostController: NavHostController = rememberNavController(),
    viewModelProfile: ProfileViewModel = hiltViewModel(),
    viewModelGoogle: GoogleViewModel = hiltViewModel(),
    viewModelRealtimeDb : RealtimeDbViewModel = hiltViewModel()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isKeyboardOpen by keyboardAsState()

    val state by viewModelGoogle.state.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )

        onDispose {}
    }

//  For Application get close by user get user data
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

            viewModelRealtimeDb.createUser(
                RealtimeDBUser(
                    name = viewModelProfile.getSignedInUser()!!.email!!.split("@")[0]
                ),
                context
            )

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
                scope.launch {
                    viewModelGoogle.googleWithIntent(intent = result.data ?: return@launch)
                }
            }
        })

    fun onGoogleClick() {
        scope.launch {
            val signInIntentSender = viewModelGoogle.setupIntentSender(context)
            launcher.launch(
                IntentSenderRequest.Builder(
                    signInIntentSender ?: return@launch
                ).build()
            )
        }
    }

    fun onSignUpClick(email: String, password: String) {
        scope.launch {
            viewModelGoogle.signUpWithEmailAndPassword(email, password)
            viewModelProfile.saveAuthProfile(name = email.split("@")[0])
        }
    }

    fun onSignInClick(email: String, password: String) {
        scope.launch {
            viewModelGoogle.signInWithEmailAndPassword(email, password)
            viewModelProfile.saveAuthProfile(name = email.split("@")[0])
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
                LoginScreen(
                    navHostController,
                    { onGoogleClick() },
                    { email, password -> onSignInClick(email, password) },
                    state.isLoading
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    navHostController,
                    { onGoogleClick() },
                    { email, password -> onSignUpClick(email, password) },
                    state.isLoading
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(userData = viewModelProfile.getSignedInUser(), navHostController)
            }
            composable(Screen.Article.route) {
                ArticleScreen(navHostController)
            }
            composable(Screen.History.route) {
                HistoryScreen(navHostController = navHostController)
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
            composable(
                Screen.DetailArticle.route,
                listOf(
                    navArgument("image") { type = NavType.StringType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("description") { type = NavType.StringType })
            ) {
                DetailArticleScreen(
                    image = Uri.decode(it.arguments?.getString("image")),
                    title = it.arguments?.getString("title")!!,
                    description = it.arguments?.getString("description")!!,
                    navHostController
                )
            }
            composable(
                Screen.CataractResult.route,
                listOf(navArgument("image") { type = NavType.StringType },
                    navArgument("resultMl") { type = NavType.StringType })
            ) {
                CataractResultScreen(
                    navHostController,
                    imageUri = Uri.decode(
                        it.arguments?.getString("image")
                    ),
                    resultMl = it.arguments?.getString("resultMl")!!
                )
            }
        }
    }
}


