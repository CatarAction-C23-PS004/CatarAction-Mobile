package com.cataractaction.ui.screen.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cataractaction.ui.components.history.CardHistory
import com.cataractaction.ui.components.history.LogoTitleHistory

@Composable
fun HistoryScreen() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 27.dp)
    ) {
        LogoTitleHistory()
        Spacer(Modifier.size(30.dp))
        LazyColumn {
            items(list.size, key = { it }) {
                CardHistory()
                Spacer(Modifier.size(14.dp))
            }
        }
    }
}