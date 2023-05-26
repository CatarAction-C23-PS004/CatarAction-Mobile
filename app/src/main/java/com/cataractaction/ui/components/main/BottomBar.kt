package com.cataractaction.ui.components.main

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.cataractaction.R
import com.cataractaction.ui.navigation.NavigationItem
import com.cataractaction.ui.navigation.Screen

@Composable
fun BottomBar(
    navHostController: NavHostController,
    currentRoute: String?
) {
    BottomNavigation {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.home),
                icon = ImageVector.vectorResource(R.drawable.home),
                icon2 = ImageVector.vectorResource(R.drawable.home_blue),
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.article),
                icon = ImageVector.vectorResource(R.drawable.article),
                icon2 = ImageVector.vectorResource(R.drawable.article_blue),
                screen = Screen.Article
            ),
            NavigationItem(
                title = stringResource(R.string.history),
                icon = ImageVector.vectorResource(R.drawable.history),
                icon2 = ImageVector.vectorResource(R.drawable.history_blue),
                screen = Screen.History
            ),
            NavigationItem(
                title = stringResource(R.string.profile),
                icon = ImageVector.vectorResource(R.drawable.profile),
                icon2 = ImageVector.vectorResource(R.drawable.profile_blue),
                screen = Screen.Profile
            ),
        )
        BottomNavigation(backgroundColor = Color.White) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == item.screen.route) item.icon2 else item.icon,
                            contentDescription = item.title,
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                                .width(20.dp)
                                .height(20.dp),
                            tint = if (currentRoute == item.screen.route) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant
                        )
                    },
                    label = {
                        Text(
                            item.title,
                            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
                            color = if (currentRoute == item.screen.route) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant
                        )
                    },
                    selected = true,
                    onClick = {
                        navHostController.navigate(item.screen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Color.Black
                )
            }
        }
    }
}
