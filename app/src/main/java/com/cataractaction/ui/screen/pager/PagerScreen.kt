package com.cataractaction.ui.screen.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cataractaction.ui.components.pager.LogoSkip
import com.cataractaction.ui.components.pager.ButtonPager
import com.cataractaction.ui.components.pager.DotPager
import com.cataractaction.ui.components.pager.ImagePager
import com.cataractaction.ui.components.pager.TextPager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(navigateToRegister: () -> Unit) {
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .padding(23.dp)
            .fillMaxSize()
    ) {
        LogoSkip(navigateToRegister)
        ImagePager(pagerState)
        TextPager(pagerState)
        Spacer(Modifier.size(36.dp))
        DotPager(pagerState)
        Spacer(Modifier.size(170.dp))
        ButtonPager(pagerState, navigateToRegister)
    }
}

