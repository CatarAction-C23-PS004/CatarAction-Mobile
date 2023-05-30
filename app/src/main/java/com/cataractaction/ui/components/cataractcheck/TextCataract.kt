package com.cataractaction.ui.components.cataractcheck

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextCataract() {
    Text(
        text = "*Image must contain 1 eye",
        modifier = Modifier.padding(start = 34.dp),
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onError
    )
    Spacer(Modifier.size(10.dp))
    Text(
        text = "Select Image from",
        modifier = Modifier.padding(start = 34.dp),
        style = MaterialTheme.typography.body2.copy(fontSize = 16.sp)
    )
}


@Composable
fun TextAnalysis() {
    Text(
        text = "Analysis Result",
        modifier = Modifier.padding(start = 34.dp),
        style = MaterialTheme.typography.body2.copy(fontSize = 16.sp)
    )
}