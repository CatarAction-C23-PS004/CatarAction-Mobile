package com.cataractaction.ui.screen.history

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cataractaction.ui.components.history.CardHistory
import com.cataractaction.ui.components.history.LogoTitleHistory
import com.cataractaction.ui.navigation.Screen
import com.cataractaction.ui.screen.viewmodel.RealtimeDbViewModel
import java.nio.charset.StandardCharsets

@Composable
fun HistoryScreen(
    viewModelRealtimeDb: RealtimeDbViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val state by viewModelRealtimeDb.stateHistory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 27.dp)
    ) {
        LogoTitleHistory()
        Spacer(Modifier.size(30.dp))
        if (state.data != null) {
            LazyColumn {
                items(state.data!!.size, key = { it }) {
                    CardHistory(
                        date = state.data!![it]?.date!!,
                        result = state.data!![it]?.result!!,
                        onCheckCataract = { navHostController.navigate(Screen.CataractCheck.route) },
                        onCataractResult = {
                            navHostController.navigate(
                                Screen.CataractResult.createRoute(
                                    Uri.encode(
                                        state.data!![it]?.image!!
                                    ),
                                    state.data!![it]?.result!!
                                )
                            )
                        }
                    )
                    Spacer(Modifier.size(14.dp))
                }
            }
        }
    }
}