package com.cataractaction.ui.components.auth

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DividerAuth() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 22.dp)
    ) {
        Divider(
            modifier = Modifier
                .padding(start = 2.dp)
                .weight(1f)
        )
        Text(
            text = "Or",
            modifier = Modifier
                .padding(start = 7.dp, end = 7.dp)
        )
        Divider(
            modifier = Modifier
                .padding(end = 2.dp)
                .weight(1f)

        )
    }
}