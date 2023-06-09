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
    object CataractCheck : Screen("cataractCheck/{uriFile}") {
        fun createRoute(uriFile: String?) = "cataractCheck/$uriFile"
    }

    object Coming : Screen("coming")
    object Camera : Screen("camera")
    object DetailArticle : Screen("detailArticle/{image}/{title}/{description}") {
        fun createRoute(image: String, title: String, description: String) =
            "detailArticle/$image/$title/$description"
    }

    object CataractResult : Screen("cataractResult/{image}/{resultMl}") {
        fun createRoute(image: String, resultMl: String) = "cataractResult/$image/$resultMl"
    }
}