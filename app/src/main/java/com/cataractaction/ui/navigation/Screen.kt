package com.cataractaction.ui.navigation

import android.net.Uri

sealed class Screen(val route: String) {
    object Pager : Screen("pager")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Article : Screen("article")
    object History : Screen("history")
    object Profile : Screen("profile")
    object CataractCheck : Screen("cataractCheck/{uriFile}"){
        fun createRoute(uriFile: String?) = "cataractCheck/$uriFile"
    }
    object Coming : Screen("coming")
    object Camera : Screen("camera")
}