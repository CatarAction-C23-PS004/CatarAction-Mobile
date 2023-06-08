package com.cataractaction.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cataractaction.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LogoSkip(navigateToRegister: () -> Unit, pagerState: PagerState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 27.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
        )
        if (pagerState.currentPage != 2) {
            Text(
                text = "Skip",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.clickable { navigateToRegister() })
        }
    }
}