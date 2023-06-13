package com.cataractaction.ui.screen.home

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cataractaction.core.domain.model.AuthGoogleUserData
import com.cataractaction.ui.components.home.DashboardHome
import com.cataractaction.ui.components.home.LogoNotif
import com.cataractaction.ui.components.main.CardArticle
import com.cataractaction.ui.navigation.Screen
import com.cataractaction.ui.screen.viewmodel.RealtimeDbViewModel

@Composable
fun HomeScreen(
    userData: AuthGoogleUserData?,
    navHostController: NavHostController,
    viewModelRealtimeDb: RealtimeDbViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf(userData?.username ?: "") }
    val context = LocalContext.current


    LaunchedEffect(key1 = name) {
        if (name.isEmpty()) {
            name = userData?.email.toString().split("@")[0]
        }
    }

    val stateArticle by viewModelRealtimeDb.stateArticle.collectAsState()

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
            name = name
        )
        Spacer(Modifier.size(30.dp))
        Text(
            text = "Recommended Article",
            style = MaterialTheme.typography.body2.copy(fontSize = 16.sp)
        )
        Spacer(Modifier.size(20.dp))
        if (stateArticle.isLoading) {
            Spacer(Modifier.size(20.dp))
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (stateArticle.data != null) {
            val articleRecommended = stateArticle.data!!.filter { it?.category == "recommended" }
            LazyColumn {
                items(articleRecommended) { data ->
                    CardArticle(
                        data?.title!!,
                        data.description!!,
                        data.image!!
                    ) {
                        navHostController.navigate(
                            Screen.DetailArticle.createRoute(
                                Uri.encode(data.image!!),
                                data.title!!,
                                data.description!!
                            )
                        )
                    }
                    Spacer(Modifier.size(24.dp))
                }
            }
        } else {
            Toast.makeText(context, stateArticle.errorMsg, Toast.LENGTH_LONG).show()
        }
    }
}