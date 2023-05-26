package com.cataractaction.ui.components.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cataractaction.R

@Composable
fun LogoTitle() {
    Column(modifier = Modifier.fillMaxWidth().padding(top = 35.dp)) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
        )
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body2.copy(fontSize = 15.sp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 33.dp)
        )
    }
}