package com.cataractaction.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cataractaction.core.domain.model.UserData
import com.cataractaction.ui.components.home.DashboardHome
import com.cataractaction.ui.components.home.LogoNotif
import com.cataractaction.ui.components.main.CardArticle
import com.cataractaction.ui.navigation.Screen

@Composable
fun HomeScreen(
    userData: UserData?,
    navHostController: NavHostController
) {
    val list = listOf(1, 2, 3, 4, 5, 6)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 27.dp)
    ) {
        LogoNotif { navHostController.navigate(Screen.Coming.route) }
        Spacer(Modifier.size(24.dp))
        DashboardHome(
            { navHostController.navigate(Screen.CataractCheck.route) },
            { navHostController.navigate(Screen.History.route) },
            name = userData?.username ?: "Not Found"
        )
        Spacer(Modifier.size(30.dp))
        Text(
            text = "Recommended Article",
            style = MaterialTheme.typography.body2.copy(fontSize = 16.sp)
        )
        Spacer(Modifier.size(20.dp))
        LazyColumn {
            items(list.size, key = { it }) {
                CardArticle()
                Spacer(Modifier.size(24.dp))
            }
        }
    }
}