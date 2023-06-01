package com.cataractaction.ui.components.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.cataractaction.core.domain.model.UserData

@Composable
fun NameProfile(name: String, email: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = email,
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colors.primaryVariant
        )
    }
}