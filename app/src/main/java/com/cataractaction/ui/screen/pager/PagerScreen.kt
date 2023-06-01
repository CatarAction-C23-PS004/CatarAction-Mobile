package com.cataractaction.ui.screen.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.cataractaction.ui.components.pager.LogoSkip
import com.cataractaction.ui.components.pager.ButtonPager
import com.cataractaction.ui.components.pager.DotPager
import com.cataractaction.ui.components.pager.ImagePager
import com.cataractaction.ui.components.pager.TextPager
import com.cataractaction.ui.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(navHostController: NavHostController) {
    val pagerState = rememberPagerState()
    val navigateToRegister = {
        navHostController.popBackStack()
        navHostController.navigate(Screen.Register.route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    Column(
        modifier = Modifier
            .padding(23.dp)
            .fillMaxSize()
    ) {
        LogoSkip(navigateToRegister)
        ImagePager(pagerState)
        TextPager(pagerState)
        Spacer(Modifier.size(36.dp))
        DotPager(pagerState)
        Spacer(Modifier.size(170.dp))
        ButtonPager(pagerState, navigateToRegister)
    }
}

