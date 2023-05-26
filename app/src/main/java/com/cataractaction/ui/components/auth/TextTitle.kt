package com.cataractaction.ui.components.auth

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cataractaction.R

@Composable
fun TextTitle(register:Boolean) {
    Text(
        text = stringResource(if (register) R.string.register_title else R.string.login_title),
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(bottom = 24.dp)
    )
    Text(
        text = stringResource(if (register) R.string.register_body else R.string.login_body),
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(bottom = 28.dp)
    )
}