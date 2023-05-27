package com.cataractaction.ui.navigation

sealed class Screen(val route: String) {
    object Pager : Screen("pager")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Article : Screen("article")
    object History : Screen("history")
    object Profile : Screen("profile")
    object CataractCheckScreen : Screen("CataractCheckScreen")
}