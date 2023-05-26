package com.cataractaction.ui.components.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TextAuth(navigate: () -> Unit, register: Boolean) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = if (register) "Already have an account ?" else "Don’t have an account ?", style = MaterialTheme.typography.body2)
        Text(
            text = if (register) " Sign In" else " Sign Up",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.clickable { navigate() }
        )
    }
}